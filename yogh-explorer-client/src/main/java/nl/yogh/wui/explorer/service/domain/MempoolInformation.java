package nl.yogh.wui.explorer.service.domain;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType
public class MempoolInformation {
  @JsProperty public int count;
  @JsProperty public int vsize;
  @JsProperty(name = "total_fee") public int totalFee;
  @JsProperty(name = "fee_histogram") public String[][] feeHistogram;
}
