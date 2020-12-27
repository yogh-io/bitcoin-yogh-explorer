package nl.yogh.wui.explorer.service.domain;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType
public class TransactionSummary {
  @JsProperty public String txid;
  @JsProperty public long fee;
  @JsProperty public long vsize;
  @JsProperty public long value;
}