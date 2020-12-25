package nl.yogh.wui.explorer.ui.block;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Computed;
import com.axellience.vuegwt.core.annotations.component.Data;
import com.axellience.vuegwt.core.annotations.component.Prop;
import com.axellience.vuegwt.core.client.component.IsVueComponent;
import com.google.web.bindery.event.shared.EventBus;

import jsinterop.annotations.JsMethod;

import nl.yogh.wui.explorer.component.links.BlockLink;
import nl.yogh.wui.explorer.component.links.TransactionLink;
import nl.yogh.wui.explorer.context.BlockContext;
import nl.yogh.wui.explorer.service.domain.BlockInformation;

@Component(components = {
    BlockLink.class,
    TransactionLink.class,
})
public class BlockView implements IsVueComponent {
  @Prop EventBus eventBus;

  @Data String hash;

  @Data @Inject BlockContext context;

  @Data int limit = 10;

  @Computed
  public BlockInformation getBlock() {
    return context.getBlockInformation();
  }

  @Computed
  public String getRaw() {
    return context.getRawBlock();
  }

  @JsMethod
  public List<String> limit(final List<String> lst) {
    return lst.stream().limit(limit).collect(Collectors.toCollection(() -> new ArrayList<>()));
  }

  @JsMethod
  public void increaseLimit() {
    limit += 5;
  }

  @Computed
  public List<String> getTxids() {
    return context.getTxids();
  }
}
