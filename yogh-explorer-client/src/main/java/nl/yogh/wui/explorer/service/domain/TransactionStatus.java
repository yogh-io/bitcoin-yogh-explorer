package nl.yogh.wui.explorer.service.domain;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType
public class TransactionStatus {
  @JsProperty(name = "block_hash") public String blockHash;
  @JsProperty(name = "block_height") public int blockHeight;
  @JsProperty(name = "block_time") public int blockTime;
  @JsProperty(name = "confirmed") public boolean confirmed;
}
