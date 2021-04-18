package nl.yogh.indexer.blk;

public class RawBlock {
  private String prevBlockHex;
  private String blockHash;

  private long blockStart;

  public static RawBlock of(final String prevBlockHash, final String blockHash, final long blockStart) {
    return new RawBlock()
        .prevBlockHash(prevBlockHash)
        .blockHash(blockHash)
        .blockStart(blockStart);
  }

  public RawBlock blockStart(final long blockStart) {
    this.blockStart = blockStart;
    return this;
  }

  public long header() {
    return blockStart;
  }

  public RawBlock blockHash(final String hash) {
    this.blockHash = hash;
    return this;
  }

  public String blockHash() {
    return blockHash;
  }

  public RawBlock prevBlockHash(final String prevBlockHex) {
    this.prevBlockHex = prevBlockHex;
    return this;
  }

  public String prevBlockHash() {
    return prevBlockHex;
  }
}
