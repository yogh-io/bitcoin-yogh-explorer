package nl.yogh.wui.explorer.service.domain;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType
public class AddressInformation {
  @JsProperty public String address;

  @JsProperty(name = "chain_stats") public TransactionStats chainStats;
  @JsProperty(name = "mempool_stats") public TransactionStats mempoolStats;
}
