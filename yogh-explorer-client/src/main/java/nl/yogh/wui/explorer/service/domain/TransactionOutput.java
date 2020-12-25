package nl.yogh.wui.explorer.service.domain;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType
public class TransactionOutput {
  @JsProperty public String scriptpubkey;
  @JsProperty(name = "scriptpubkey_address") public String scriptpubkeyAddress;
  @JsProperty(name = "scriptpubkey_asm") public String scriptpubkeyAsm;
  @JsProperty(name = "scriptpubkey_type") public String scriptpubkeyType;

  @JsProperty public int value;
}
