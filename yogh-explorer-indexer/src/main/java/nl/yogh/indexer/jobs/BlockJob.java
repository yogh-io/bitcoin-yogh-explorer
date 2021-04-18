package nl.yogh.indexer.jobs;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.yogh.indexer.util.VariableLengthInteger;

import kong.unirest.Unirest;

public final class BlockJob {
  private static final Logger LOG = LoggerFactory.getLogger(BlockJob.class);

  private int height;

  private final List<BlockTask> blockTasks = new ArrayList<>();
  private final List<TransactionTask> transactionTasks = new ArrayList<>();

  private BlockJob() {}

  public static BlockJob create(final int height) {
    return new BlockJob()
        .height(height);
  }

  private BlockJob height(final int height) {
    this.height = height;
    return this;
  }

  public BlockJob blockTask(final BlockTask task) {
    blockTasks.add(task);
    return this;
  }

  public BlockJob transactionTask(final TransactionTask task) {
    transactionTasks.add(task);
    return this;
  }

  public BlockJobResult process() {
    final BlockJobResult result = new BlockJobResult();

    final String hash = Unirest.get("http://localhost:3000/block-height/" + height)
        .asString().getBody();

    final byte[] block = Unirest.get("http://localhost:3000/block/" + hash + "/raw")
        .asBytes().getBody();

    final byte[] header = new byte[80];
    System.arraycopy(block, 0, header, 0, 80);

    blockTasks.forEach(v -> v.accept(result, header));

    final VariableLengthInteger transactionCount = new VariableLengthInteger(block, 80);
    int pointer = 80 + transactionCount.getByteSize();
    for (int i = 0; i < transactionCount.getValue(); i++) {
      final int length = TransactionUtil.fetchTransactionLength(block, pointer);

      final byte[] tx = new byte[length];
      System.arraycopy(block, pointer, tx, 0, length);
      
      final int idx = i;

      transactionTasks.forEach(v -> v.accept(result, idx, tx));

      pointer += length;
    }

    return result;
  }
}
