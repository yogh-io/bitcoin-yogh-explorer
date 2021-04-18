package nl.yogh.indexer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import nl.yogh.indexer.blk.CoreBlkIndexer;
import nl.yogh.indexer.jobs.BlockJob;

@SpringBootApplication
public class IndexerApplication {
  private static final Logger LOG = LoggerFactory.getLogger(BlockJob.class);

  public static void main(final String[] args) throws IOException {
//    SpringApplication.run(IndexerApplication.class, args);

    final long start = System.currentTimeMillis();
    CoreBlkIndexer.updateIndex(args[0]);
    final long end = System.currentTimeMillis();

    LOG.info("Total execution time: {}ms", end - start);
//    BlockIndexer.doit(args[0]);
  }
}
