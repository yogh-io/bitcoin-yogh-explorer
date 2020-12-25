package nl.yogh.wui.explorer.service.domain;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType
public class TransactionInput {
  @JsProperty(name = "is_coinbase") public boolean isCoinbase;

  @JsProperty public String scriptsig;
  @JsProperty(name = "scriptsig_asm") public String scriptsigAsm;
  @JsProperty public int sequence;
  
  @JsProperty public String txid;
  @JsProperty public int vout;
  
  @JsProperty public String[] witness;
}
