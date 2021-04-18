package nl.yogh.wui.explorer.service.domain;

import static jsinterop.annotations.JsPackage.GLOBAL;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(namespace = GLOBAL, name = "Object", isNative = true)
public class BlockInformation {
  @JsProperty public String bits;
  @JsProperty public String difficulty;
  @JsProperty public int height;

  @JsProperty public String id;

  @JsProperty(name = "merkle_root") public String merkleRoot;

  @JsProperty public int nonce;
  @JsProperty(name = "previousblockhash") public String previousBlockHash;
  @JsProperty public int size;
  @JsProperty public int timestamp;
  @JsProperty(name = "tx_count") public int txCount;
  @JsProperty public String version;
  @JsProperty public int weight;
}
