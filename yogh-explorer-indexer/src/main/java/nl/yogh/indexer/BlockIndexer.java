package nl.yogh.indexer;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.yogh.indexer.jobs.BlockJob;

public final class BlockIndexer {
  private static final Logger LOG = LoggerFactory.getLogger(BlockIndexer.class);

  private BlockIndexer() {}

  public static void doit(final String dir) {
    final long start = System.currentTimeMillis();

    final AtomicLong intermediateTime = new AtomicLong(System.currentTimeMillis());
    final AtomicInteger transactionCounter = new AtomicInteger();

    IntStream.range(0, 100000)
        .forEach(height -> {
          BlockJob.create(height)
              .blockTask((result, block) -> {
                if (height % 5000 == 0) {
                  LOG.info("Height: {}", height);

                  final long now = System.currentTimeMillis();
                  LOG.info("Execution time for {} transactions: {}ms", transactionCounter.get(), now - intermediateTime.get());
                  
                  transactionCounter.set(0);
                  intermediateTime.set(System.currentTimeMillis());
                  System.out.println();
                }
              })
              .transactionTask((result, idx, tx) -> {
                transactionCounter.addAndGet(1);
                // LOG.info("Tx #{} size: {}", idx, tx.length);
              })
              .process();
        });

    final long stop = System.currentTimeMillis();

    LOG.info("Execution time: {}ms", stop - start);
  }
}
