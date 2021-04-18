package nl.yogh.wui.explorer.service.domain;

import static jsinterop.annotations.JsPackage.GLOBAL;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(namespace = GLOBAL, name = "Object", isNative = true)
public class TransactionOutput {
  @JsProperty public String scriptpubkey;
  @JsProperty(name = "scriptpubkey_address") public String scriptpubkeyAddress;
  @JsProperty(name = "scriptpubkey_asm") public String scriptpubkeyAsm;
  @JsProperty(name = "scriptpubkey_type") public String scriptpubkeyType;

  @JsProperty public int value;
}
