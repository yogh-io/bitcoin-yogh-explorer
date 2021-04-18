package nl.yogh.indexer.blk;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChainConstructor {
  private static final Logger LOG = LoggerFactory.getLogger(ChainConstructor.class);

  private final Map<String, DetachedChain> chains = new HashMap<>();
  private final Map<String, DetachedChain> orphans = new HashMap<>();

  private final String handle;
  private final int idx;

  public ChainConstructor(final String handle, final int idx) {
    this.handle = handle;
    this.idx = idx;
  }

  public void add(final DetachedChain chain) {
    final boolean canAppendToChain = chains.containsKey(chain.getPrevBlockHash()) && chain.size() > 3;
    final boolean canPrependToOrphan = orphans.containsKey(chain.getEndHash());

//    LOG.info("Adding chain [{} - {}] canAppendToChain:{} canPrependToOrphan:{}",
//        chain.getStartHash(), chain.getEndHash(),
//        canAppendToChain, canPrependToOrphan);

    if (canAppendToChain) {
//      LOG.info("Appending {} to {}", chain.getStartHash(), chain.getPrevBlockHash());
      final DetachedChain expandedChain = chains.remove(chain.getPrevBlockHash());
      expandedChain.append(chain);

      if (canPrependToOrphan) {
//        LOG.info("Prepending (inner) {} to {}", expandedChain.getStartHash(), chain.getEndHash());
        expandedChain.append(orphans.remove(chain.getEndHash()));
      }

      chains.put(expandedChain.getEndHash(), expandedChain);
    } else if (canPrependToOrphan) {
//      LOG.info("Prepending {} to {}", chain.getStartHash(), chain.getEndHash());
      orphans.put(chain.getPrevBlockHash(), orphans.remove(chain.getEndHash()).prepend(chain));
    } else {
      chains.put(chain.getEndHash(), chain);
      orphans.put(chain.getPrevBlockHash(), chain);
    }
//    
//    report(orphans, chains);
//    System.out.println();
  }

  public void add(final RawBlock block) {
    add(DetachedChain.of(block));
  }

  public Map<String, DetachedChain> getOrphans() {
    return orphans;
  }

  public Map<String, DetachedChain> getChains() {
    return chains;
  }
  
  private static void report(final Map<String, DetachedChain> orphans, final Map<String, DetachedChain> chains) {
    if (!chains.isEmpty()) {
      chains.forEach((k, v) -> {
        LOG.info("Chain [{}] ({}-{}): #{} -> {}", k, v.getStartHash(), v.getEndHash(), v.getBlocks().size(), v.getBlocks().stream().limit(5)
            .map(e -> "[" + e.prevBlockHash() + ">" + e.blockHash() + "]")
            .collect(Collectors.joining(",")));
      });
    }

    if (!orphans.isEmpty()) {
      orphans.forEach((k, v) -> {
        LOG.info("Orphan [{}] ({}-{}): #{} -> {}", k, v.getStartHash(), v.getEndHash(), v.getBlocks().size(), v.getBlocks().stream().limit(5)
            .map(e -> "[" + e.prevBlockHash() + ">" + e.blockHash() + "]")
            .collect(Collectors.joining(",")));
      });
    }
    LOG.info("Orphans: #{} Chains: #{}", orphans.size(), chains.size());
  }

  public String getHandle() {
    return handle;
  }

  public static ChainConstructor get(final String handle) {
    return new ChainConstructor(handle, 0);
  }

  public static ChainConstructor get(final int idx) {
    return new ChainConstructor(String.valueOf(idx), idx);
  }

  public int getIdx() {
    return idx;
  }

  public ChainConstructor combine(final ChainConstructor b) {
    b.chains.values().forEach(v -> add(v));
    return this;
  }
}
