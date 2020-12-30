package nl.yogh.wui.explorer.service.domain;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType
public class UtxoInformation {
  @JsProperty public String txid;
  @JsProperty public int vout;
  @JsProperty public int value;
  @JsProperty public TransactionStatus status;
}
