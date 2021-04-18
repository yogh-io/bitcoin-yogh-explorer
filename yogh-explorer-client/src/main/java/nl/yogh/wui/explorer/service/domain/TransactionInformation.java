package nl.yogh.wui.explorer.service.domain;

import static jsinterop.annotations.JsPackage.GLOBAL;

import com.axellience.vuegwt.core.annotations.component.Data;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(namespace = GLOBAL, name = "Object", isNative = true)
public class TransactionInformation {
  @JsProperty public int fee;
  @JsProperty public int locktime;
  @JsProperty public int size;

  @JsProperty public TransactionStatus status;

  @JsProperty @Data public TransactionInput[] vin;
  @JsProperty @Data public TransactionOutput[] vout;

  @JsProperty public String txid;
  @JsProperty public int version;

  @JsProperty public int weight;
}
