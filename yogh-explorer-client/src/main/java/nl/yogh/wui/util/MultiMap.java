package nl.yogh.wui.util;

import java.util.ArrayList;
import java.util.List;

public class MultiMap<K, V> extends AutoFillingHashMap<K, List<V>> {
  private static final long serialVersionUID = 7987334298722116268L;

  @Override
  protected List<V> createEmptyRecord(final K key) {
    return new ArrayList<>();
  }
}
