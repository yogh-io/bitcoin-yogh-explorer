package nl.yogh.indexer.jobs;

public interface BlockTask {
  void accept(BlockJobResult result, byte[] block);
}
