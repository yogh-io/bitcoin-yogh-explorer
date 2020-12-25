package nl.yogh.wui.explorer.service.domain;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType
public class UtxoInformation {
  @JsProperty public String txid;
  @JsProperty public String vout;
  @JsProperty public String value;
  @JsProperty public TransactionStatus status;
}
