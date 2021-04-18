package nl.yogh.indexer.blk;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class BlkJob {
  private Path blk;

  private final List<Consumer<byte[]>> blockConsumers = new ArrayList<>();

  private BlkJob() {}

  public static BlkJob create(final Path blk) {
    return new BlkJob()
        .blk(blk);
  }

  public BlkJob blk(final Path blk) {
    this.blk = blk;
    return this;
  }

  public BlkJob blockConsumer(final Consumer<byte[]> blockConsumer) {
    blockConsumers.add(blockConsumer);
    return this;
  }

  public void process() {

  }
}
