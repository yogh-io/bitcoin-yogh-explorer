package nl.yogh.wui.explorer.service.domain;

import static jsinterop.annotations.JsPackage.GLOBAL;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(namespace = GLOBAL, name = "Object", isNative = true)
public class TransactionSummary {
  @JsProperty public String txid;
  @JsProperty public int fee;
  @JsProperty public int vsize;
  @JsProperty public int value;
}
