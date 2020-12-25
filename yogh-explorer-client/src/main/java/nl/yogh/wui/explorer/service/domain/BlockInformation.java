package nl.yogh.wui.explorer.service.domain;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType
public class BlockInformation {
  @JsProperty public String bits;
  @JsProperty public String difficulty;
  @JsProperty public int height;

  @JsProperty public String id;

  @JsProperty(name = "merkle_root") public String merkleRoot;

  @JsProperty public String nonce;
  @JsProperty(name = "previousblockhash") public String previousBlockHash;
  @JsProperty public String size;
  @JsProperty public String timestamp;
  @JsProperty(name = "tx_count") public String txCount;
  @JsProperty public String version;
  @JsProperty public String weight;
}
