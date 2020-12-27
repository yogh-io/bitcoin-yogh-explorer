package nl.yogh.wui.explorer.service.domain;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType
public class MempoolInformation {
  @JsProperty public String count;
  @JsProperty public String vsize;
  @JsProperty(name = "total_fee") public String totalFee;
  @JsProperty(name = "fee_histogram") public String[][] feeHistogram;
}
