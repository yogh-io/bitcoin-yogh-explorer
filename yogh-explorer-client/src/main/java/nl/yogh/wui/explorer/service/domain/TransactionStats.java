package nl.yogh.wui.explorer.service.domain;

import static jsinterop.annotations.JsPackage.GLOBAL;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(namespace = GLOBAL, name = "Object", isNative = true)
public class TransactionStats {
  @JsProperty(name = "funded_txo_count") public int fundedTxoCount;
  @JsProperty(name = "funded_txo_sum") public int fundedTxoSum;
  @JsProperty(name = "spent_txo_count") public int spentTxoCount;
  @JsProperty(name = "spent_txo_sum") public int spentTxoSum;
  @JsProperty(name = "tx_count") public int txCount;
}
