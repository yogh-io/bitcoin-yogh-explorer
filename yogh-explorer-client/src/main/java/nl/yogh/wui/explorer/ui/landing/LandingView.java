package nl.yogh.wui.explorer.ui.landing;

import javax.inject.Inject;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Data;
import com.axellience.vuegwt.core.annotations.component.Prop;
import com.axellience.vuegwt.core.client.component.IsVueComponent;
import com.google.web.bindery.event.shared.EventBus;

import nl.yogh.wui.explorer.component.links.BlockLink;
import nl.yogh.wui.explorer.context.OverviewContext;

@Component(components = {
    BlockLink.class
})
public class LandingView implements IsVueComponent {
  @Prop EventBus eventBus;

  @Inject @Data OverviewContext context;

}
