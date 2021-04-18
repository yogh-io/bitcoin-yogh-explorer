package nl.yogh.wui.explorer.service.domain;

import static jsinterop.annotations.JsPackage.GLOBAL;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(namespace = GLOBAL, name = "Object", isNative = true)
public class UtxoInformation {
  @JsProperty public String txid;
  @JsProperty public int vout;
  @JsProperty public int value;
  @JsProperty public TransactionStatus status;
}
