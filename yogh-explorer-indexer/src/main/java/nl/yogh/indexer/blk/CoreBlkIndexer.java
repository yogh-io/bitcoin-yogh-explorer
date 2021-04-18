package nl.yogh.indexer.blk;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.yogh.indexer.util.ArrayUtil;
import nl.yogh.indexer.util.NumberParseUtil;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.util.concurrent.Queues;

public final class CoreBlkIndexer {
  private static final Logger LOG = LoggerFactory.getLogger(CoreBlkIndexer.class);

  private static final int THREADS = 8;

  private CoreBlkIndexer() {}

  public static void updateIndex(final String blkDir) throws IOException {
    log("Finding index in: {}", blkDir);
    final ChainConstructor chain = Flux.fromStream(Files.list(Paths.get(blkDir))
        .filter(v -> v.getFileName().toString().startsWith("blk"))
        .sorted()
    // .limit(4)
    )
        .parallel(THREADS)
        .runOn(Schedulers.parallel())
        .map(CoreBlkIndexer::consumePath)
        .ordered((o1, o2) -> Integer.compare(o1.getIdx(), o2.getIdx()), Queues.XS_BUFFER_SIZE)
        .reduce((a, b) -> a.combine(b))
        .doOnNext(v -> {
          LOG.info("Block: {}", v.getHandle());
        })
        .block();

    report(chain.getChains(), chain.getOrphans());
  }

  private static void report(final Map<String, DetachedChain> chainBeginnings, final Map<String, DetachedChain> chainEndings) {
    if (!chainEndings.isEmpty()) {
      chainEndings.forEach((k, v) -> {
        LOG.info("Ending [{}] ({}-{}): #{} -> {}", k, v.getStartHash(), v.getEndHash(), v.getBlocks().size(), v.getBlocks().stream().limit(5)
            .map(e -> "[" + e.prevBlockHash() + ">" + e.blockHash() + "]")
            .collect(Collectors.joining(",")));
      });
    }

    if (!chainBeginnings.isEmpty()) {
      chainBeginnings.forEach((k, v) -> {
        LOG.info("Beginning [{}] ({}-{}): #{} -> {}", k, v.getStartHash(), v.getEndHash(), v.getBlocks().size(), v.getBlocks().stream().limit(5)
            .map(e -> "[" + e.prevBlockHash() + ">" + e.blockHash() + "]")
            .collect(Collectors.joining(",")));
      });
    }
    LOG.info("Orphans: #{} Chains: #{}", chainBeginnings.size(), chainEndings.size());
  }

  private static ChainConstructor consumePath(final Path path) {
    final int idx = findIdx(path.getFileName().toString());
    LOG.info("Consuming: {} -> {}", idx, path.toString());

    final ChainConstructor constructor = ChainConstructor.get(idx);

    long pointer = 0;

    try (RandomAccessFile stream = new RandomAccessFile(path.toFile(), "r")) {
      final long length = stream.length();
      while (pointer < length) {
        pointer += 4;
        stream.seek(pointer);

        final byte[] lenAndHeader = new byte[84];
        stream.read(lenAndHeader);

        final long blockStart = pointer + 4;

        final byte[] header = new byte[80];
        System.arraycopy(lenAndHeader, 4, header, 0, 80);
        final byte[] prevBlock = new byte[32];
        System.arraycopy(lenAndHeader, 8, prevBlock, 0, 32);
        ArrayUtil.reverse(prevBlock);
        final String prevBlockHex = hex(prevBlock);

        final byte[] blockHash = digest(digest(header));
        ArrayUtil.reverse(blockHash);
        final String blockHashHex = hex(blockHash);

        final RawBlock rawBlock = RawBlock.of(prevBlockHex, blockHashHex, blockStart);
        constructor.add(rawBlock);

        final byte[] len = new byte[4];
        System.arraycopy(lenAndHeader, 0, len, 0, 4);
        pointer += NumberParseUtil.parseUint32(len) + 4;
      }

      LOG.info("Done.");

      return constructor;
    } catch (final IOException e) {
      throw new RuntimeException(e);
//    } catch (final InterruptedException e) {
//      throw new RuntimeException(e);
    }
  }

  private static int findIdx(final String string) {
    try {
      return Integer.parseInt(string.substring(3, 8).replaceAll("^0+", ""));
    } catch (final NumberFormatException e) {
      return 0;
    }
  }

  public static void attach(final Map<String, List<RawBlock>> chains, final Map<String, List<RawBlock>> orphans,
      final String prevBlockHex, final String blockHashHex, final RawBlock rawBlock) {
    //
  }

  private static void log(final String string, final Object... collect) {
    LOG.info(string, collect);
  }

  private static byte[] digest(final byte[] header) {
    final MessageDigest digest = fetchAlgo();
    final byte[] hash = digest.digest(header);
    return hash;
  }

  private static MessageDigest fetchAlgo() {
    MessageDigest digest;
    try {
      digest = MessageDigest.getInstance("SHA-256");
    } catch (final NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
    return digest;
  }

  private static String hex(final byte b) {
    return hex(new byte[] { b });
  }

  private static String hex(final byte b[]) {
    return new String(Hex.encodeHex(b));
  }
}
