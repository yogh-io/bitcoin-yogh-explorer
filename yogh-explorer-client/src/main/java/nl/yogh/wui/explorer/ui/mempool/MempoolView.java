package nl.yogh.wui.explorer.ui.mempool;

import javax.inject.Inject;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Data;
import com.axellience.vuegwt.core.annotations.component.Prop;
import com.axellience.vuegwt.core.client.component.IsVueComponent;
import com.google.web.bindery.event.shared.EventBus;

import nl.yogh.wui.explorer.component.links.AddressLink;
import nl.yogh.wui.explorer.component.links.BlockLink;
import nl.yogh.wui.explorer.component.links.TransactionLink;
import nl.yogh.wui.explorer.context.MempoolContext;

@Component(components = {
    BlockLink.class,
    TransactionLink.class,
    AddressLink.class,
})
public class MempoolView implements IsVueComponent {
  @Prop EventBus eventBus;

  @Data @Inject MempoolContext context;

}
