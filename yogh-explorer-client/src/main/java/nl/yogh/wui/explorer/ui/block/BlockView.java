package nl.yogh.wui.explorer.ui.block;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Computed;
import com.axellience.vuegwt.core.annotations.component.Data;
import com.axellience.vuegwt.core.annotations.component.Prop;
import com.axellience.vuegwt.core.client.component.IsVueComponent;
import com.google.web.bindery.event.shared.EventBus;

import jsinterop.annotations.JsMethod;

import nl.yogh.wui.explorer.component.hex.HexViewer;
import nl.yogh.wui.explorer.component.hex.interpreters.BlockInterpreter;
import nl.yogh.wui.explorer.component.hex.interpreters.TransactionInterpreter;
import nl.yogh.wui.explorer.component.links.AddressLink;
import nl.yogh.wui.explorer.component.links.BlockLink;
import nl.yogh.wui.explorer.component.links.TransactionLink;
import nl.yogh.wui.explorer.context.BlockContext;
import nl.yogh.wui.explorer.service.domain.BlockInformation;

@Component(components = {
    BlockLink.class,
    TransactionLink.class,
    AddressLink.class,
    HexViewer.class
})
public class BlockView implements IsVueComponent {
  @Prop EventBus eventBus;

  @Data @Inject BlockContext context;

  @Data int limit = 10;
  
  @Inject @Data BlockInterpreter blockInterpreter;
  @Inject @Data TransactionInterpreter transactionInterpreter;

  @Computed
  public BlockInformation getBlock() {
    return context.blockInformation;
  }

  @Computed
  public String getRaw() {
    return context.raw;
  }

  @Computed
  public String[] getTxids() {
    return context.txids;
  }

  @JsMethod
  public List<String> limit(final String[] lst) {
    return Stream.of(lst)
        .limit(limit)
        .collect(Collectors.toList());
  }

  @JsMethod
  public void increaseLimit() {
    limit += 5;
  }
}
