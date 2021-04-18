package nl.yogh.indexer.blk;

import java.util.ArrayList;
import java.util.List;

public class DetachedChain {
  private String prevBlockHash;

  private String startHash;
  private String endHash;

  private ArrayList<RawBlock> blocks;

  public static DetachedChain of(final RawBlock block) {
    final ArrayList<RawBlock> lst = new ArrayList<>();
    lst.add(block);

    final DetachedChain chain = new DetachedChain();
    chain.blocks = lst;
    chain.prevBlockHash = block.prevBlockHash();
    chain.startHash = block.blockHash();
    chain.endHash = block.blockHash();
    return chain;
  }

  public String getStartHash() {
    return startHash;
  }

  public String getEndHash() {
    return endHash;
  }

  public List<RawBlock> getBlocks() {
    return blocks;
  }

  public DetachedChain append(final DetachedChain chain) {
    blocks.addAll(chain.getBlocks());
    endHash = chain.getEndHash();
    return this;
  }

  public DetachedChain append(final RawBlock block) {
    blocks.add(block);
    endHash = block.blockHash();
    return this;
  }

  public String getPrevBlockHash() {
    return prevBlockHash;
  }

  public DetachedChain prepend(final DetachedChain chain) {
    blocks.addAll(0, chain.getBlocks());
    startHash = chain.getStartHash();
    prevBlockHash = chain.getPrevBlockHash();
    return this;
  }

  public DetachedChain prepend(final RawBlock block) {
    blocks.add(0, block);
    startHash = block.blockHash();
    prevBlockHash = block.prevBlockHash();
    return this;
  }

  public int size() {
    return blocks.size();
  }
}
