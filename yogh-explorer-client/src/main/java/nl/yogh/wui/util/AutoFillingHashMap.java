package nl.yogh.wui.util;

import java.util.HashMap;
import java.util.function.Supplier;

public class AutoFillingHashMap<K, V> extends HashMap<K, V> {
  private static final long serialVersionUID = -4040982204816673692L;

  private Supplier<V> emptyRecordSupplier;

  public AutoFillingHashMap() {}

  public AutoFillingHashMap(final Supplier<V> emptyRecordSupplier) {
    this.emptyRecordSupplier = emptyRecordSupplier;

  }

  protected V createEmptyRecord(final K key) {
    if (emptyRecordSupplier != null) {
      return emptyRecordSupplier.get();
    }

    throw new RuntimeException("createEmptyRecord(K) has not been implemented or an emptyRecordSupplier has not been provided.");
  }

  @Override
  @SuppressWarnings("unchecked")
  public V get(final Object key) {
    if (!containsKey(key)) {
      put((K) key, createEmptyRecord((K) key));
    }

    return super.get(key);

  }
}
