package nl.yogh.wui.explorer.service.domain;

import static jsinterop.annotations.JsPackage.GLOBAL;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(namespace = GLOBAL, name = "Object", isNative = true)
public class MempoolInformation {
  @JsProperty public int count;
  @JsProperty public int vsize;
  @JsProperty(name = "total_fee") public int totalFee;
  @JsProperty(name = "fee_histogram") public double[][] feeHistogram;
}
