package nl.yogh.wui.explorer.service.domain;

import com.axellience.vuegwt.core.annotations.component.Data;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType
public class TransactionInformation {
  @JsProperty public int fee;
  @JsProperty public int locktime;
  @JsProperty public int size;

  @JsProperty public TransactionStatus status = new TransactionStatus();

  @JsProperty @Data public TransactionInput[] vin;
  @JsProperty @Data public TransactionOutput[] vout;

  @JsProperty public String txid;
  @JsProperty public int version;

  @JsProperty public int weight;
}
