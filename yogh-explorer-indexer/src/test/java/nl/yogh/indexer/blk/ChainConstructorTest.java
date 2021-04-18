package nl.yogh.indexer.blk;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChainConstructorTest {
  private static final Logger LOG = LoggerFactory.getLogger(ChainConstructorTest.class);

  // @Test
  public void testAttachRandom() {
    final String blox = "abcdefg";// hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    final List<RawBlock> blocks = new ArrayList<>();
    for (int i = 0; i < blox.length(); i++) {
      blocks.add(RawBlock.of(
          i == 0 ? "nil" : new String(new char[] { blox.charAt(i - 1) }),
          new String(new char[] { blox.charAt(i) }),
          0L));
    }

    for (int j = 0; j < 10; j++) {
      Collections.shuffle(blocks);

      LOG.info("{}", blocks.stream().map(v -> v.blockHash()).collect(Collectors.joining(",")));

      final ChainConstructor constructor = ChainConstructor.get("random#" + j);
      blocks.forEach(v -> constructor.add(v));
      validateSimple(constructor);
    }
  }

  @Test
  public void testOrphaned() {
    final ChainConstructor constructor1 = ChainConstructor.get("Conflict1");

    constructor1.add(RawBlock.of("a", "b", 0L));
    
    constructor1.add(RawBlock.of("b", "c", 0L));
    constructor1.add(RawBlock.of("b", "x", 0L));
    
    constructor1.add(RawBlock.of("c", "d", 0L));
    constructor1.add(RawBlock.of("d", "e", 0L));
    report(constructor1);

    final ChainConstructor constructor2 = ChainConstructor.get("Conflict2");
    constructor2.add(RawBlock.of("a", "b", 0L));
    
    constructor2.add(RawBlock.of("b", "x", 0L));
    constructor2.add(RawBlock.of("b", "c", 0L));
    
    constructor2.add(RawBlock.of("c", "d", 0L));
    constructor1.add(RawBlock.of("d", "e", 0L));
    report(constructor2);
  }

  @Test
  public void testChainedFrontToBack() {
    final ChainConstructor constructor = ChainConstructor.get("ChainedBetween");

    constructor.add(RawBlock.of("e", "f", 0L));
    constructor.add(RawBlock.of("d", "e", 0L));
    constructor.add(RawBlock.of("c", "d", 0L));
    constructor.add(RawBlock.of("f", "g", 0L));
    constructor.add(RawBlock.of("nil", "a", 0L));
    constructor.add(RawBlock.of("a", "b", 0L));
    constructor.add(RawBlock.of("b", "c", 0L));

    validateSimple(constructor);
  }

  @Test
  public void testChainedBetween() {
    final ChainConstructor constructor = ChainConstructor.get("ChainedBetween");

    constructor.add(RawBlock.of("a", "b", 0L));
    constructor.add(RawBlock.of("b", "c", 0L));

    constructor.add(RawBlock.of("e", "f", 0L));
    constructor.add(RawBlock.of("f", "g", 0L));

    constructor.add(RawBlock.of("c", "d", 0L));
    constructor.add(RawBlock.of("d", "e", 0L));

    validateSimple(constructor);
  }

  @Test
  public void testAttachBefore() {
    final ChainConstructor constructor = ChainConstructor.get("AttachBefore");

    constructor.add(RawBlock.of("b", "c", 0L));
    constructor.add(RawBlock.of("c", "d", 0L));
    constructor.add(RawBlock.of("a", "b", 0L));

    validateSimple(constructor);
  }

  @Test
  public void testAttachBetween() {
    final ChainConstructor constructor = ChainConstructor.get("AttachBetween");

    constructor.add(RawBlock.of("a", "b", 0L));
    constructor.add(RawBlock.of("c", "d", 0L));
    constructor.add(RawBlock.of("b", "c", 0L));

    validateSimple(constructor);
  }

  @Test
  public void testAttachAfter() {
    final ChainConstructor constructor = ChainConstructor.get("AttachAfter");

    constructor.add(RawBlock.of("a", "b", 0L));
    constructor.add(RawBlock.of("b", "c", 0L));
    constructor.add(RawBlock.of("c", "d", 0L));

    validateSimple(constructor);
  }

  private void validateSimple(final ChainConstructor constructor) {
    report(constructor);

    final Map<String, DetachedChain> orphans = constructor.getOrphans();
    final Map<String, DetachedChain> chains = constructor.getChains();
    assertEquals(1, orphans.size(), "Incorrect number of orphans");
    assertEquals(1, chains.size(), "Incorrect number of chains");

    checkOrder(chains.values().iterator().next().getBlocks());
  }

  private void report(final ChainConstructor constructor) {
    LOG.info("Testing [{}]", constructor.getHandle());

    final Map<String, DetachedChain> orphans = constructor.getOrphans();
    final Map<String, DetachedChain> chains = constructor.getChains();
    report(orphans, chains);
    System.out.println();
  }

  private void checkOrder(final List<RawBlock> list) {
    String prev = list.get(0).prevBlockHash();
    for (int i = 0; i < list.size(); i++) {
      final RawBlock item = list.get(i);

      assertEquals(prev, item.prevBlockHash(), "Chain is in incorrect order");
      prev = item.blockHash();
    }
  }

  private void report(final Map<String, DetachedChain> orphans, final Map<String, DetachedChain> chains) {
    LOG.info("Orphans: #{} Chains: #{}", orphans.size(), chains.size());

    if (!chains.isEmpty()) {
      chains.forEach((k, v) -> {
        LOG.info("Chain [{}] ({}-{}): {}", k, v.getStartHash(), v.getEndHash(), v.getBlocks().stream()
            .map(e -> "[" + e.prevBlockHash() + ">" + e.blockHash() + "]")
            .collect(Collectors.joining(",")));
      });
    }

    if (!chains.isEmpty()) {
      orphans.forEach((k, v) -> {
        LOG.info("Orphan [{}] ({}-{}): {}", k, v.getStartHash(), v.getEndHash(), v.getBlocks().stream()
            .map(e -> "[" + e.prevBlockHash() + ">" + e.blockHash() + "]")
            .collect(Collectors.joining(",")));
      });
    }
  }
}
