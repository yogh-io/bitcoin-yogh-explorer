package nl.yogh.wui.explorer.context;

import javax.inject.Singleton;

import com.axellience.vuegwt.core.annotations.component.Data;

import jsinterop.annotations.JsProperty;

import nl.yogh.wui.explorer.service.domain.BlockInformation;

@Singleton
public class BlockContext {
  @Data private BlockInformation tip = null;

  @Data BlockInformation blockInformation = null;
  @Data String raw = null;

  @Data @JsProperty private String[] txids = null;

  @Data public boolean blockLoading;
  @Data public boolean rawLoading;
  @Data public boolean txidsLoading;

  @Data Throwable failure = null;

  public void setBlockInformation(final BlockInformation blockInformation) {
    blockLoading = false;
    this.blockInformation = blockInformation;
  }

  public void setRawBlock(final String raw) {
    rawLoading = false;
    this.raw = raw;
  }

  public void setTxids(final String[] txids) {
    txidsLoading = false;
    this.txids = txids;
  }

  public void setFailure(final Throwable e) {
    clear();
    failure = e;
  }

  public void clear() {
    blockInformation = null;
    raw = null;
    failure = null;
    txids = null;
  }

  public BlockInformation getBlockInformation() {
    return blockInformation;
  }

  public String getRawBlock() {
    return raw;
  }

  public String[] getTxids() {
    return txids;
  }

  public BlockInformation getTip() {
    return tip;
  }

  public void setTip(final BlockInformation tip) {
    this.tip = tip;
  }

  public void setLoading() {
    blockLoading = true;
    rawLoading = true;
    txidsLoading = true;
  }
}
