package nl.yogh.indexer.jobs;

public interface TransactionTask {
  void accept(BlockJobResult result, int idx, byte[] tx);
}
