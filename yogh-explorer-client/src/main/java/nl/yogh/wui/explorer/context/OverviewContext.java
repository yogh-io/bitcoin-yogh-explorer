package nl.yogh.wui.explorer.context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import com.axellience.vuegwt.core.annotations.component.Data;

import jsinterop.annotations.JsProperty;

import nl.yogh.wui.explorer.service.domain.BlockInformation;

@Singleton
public class OverviewContext {
  @Data @JsProperty private List<BlockInformation> blocks = new ArrayList<>();

  @Data Throwable failure = null;

  public void setFailure(final Throwable e) {
    clear();
    failure = e;
  }

  public void clear() {
    failure = null;
  }

  public void setRecentBlocks(final List<BlockInformation> blocks) {
    this.blocks = blocks;
  }

  public List<BlockInformation> getRecentBlocks() {
    return blocks;
  }
}
