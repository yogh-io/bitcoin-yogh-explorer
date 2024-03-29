package nl.yogh.wui.explorer.context;

import java.util.List;

import javax.inject.Singleton;

import com.axellience.vuegwt.core.annotations.component.Data;

import jsinterop.annotations.JsProperty;

import nl.yogh.wui.explorer.service.domain.BlockInformation;

@Singleton
public class BlockContext {
  @Data public BlockInformation tip = null;

  @Data public BlockInformation blockInformation = null;
  @Data public String raw = null;
  @Data @JsProperty public List<String> txids = null;
  @Data public String coinbaseHex = null;

  @Data public boolean blockLoading;
  @Data public boolean rawLoading;
  @Data public boolean txidsLoading;
  @Data public boolean coinbaseLoading;

  @Data public Throwable failure = null;

  public void setBlockInformation(final BlockInformation blockInformation) {
    blockLoading = false;
    this.blockInformation = blockInformation;
  }

  public void setRawBlock(final String raw) {
    rawLoading = false;
    this.raw = raw;
  }

  public void setTxids(final List<String> txids) {
    txidsLoading = false;
    this.txids = txids;
  }

  public void setTip(final BlockInformation tip) {
    this.tip = tip;
  }

  public void setRawCoinbase(final String coinbaseHex) {
    coinbaseLoading = false;
    this.coinbaseHex = coinbaseHex;
  }

  public void setFailure(final Throwable e) {
    clear();
    failure = e;
  }

  public void setLoading() {
    coinbaseLoading = true;
    blockLoading = true;
    rawLoading = true;
    txidsLoading = true;
  }

  public void clear() {
    softClear();
    blockInformation = null;
    raw = null;
    txids = null;
    coinbaseHex = null;
  }

  public void softClear() {
    failure = null;
  }
}
