package nl.yogh.wui.explorer.component.application;

import javax.inject.Inject;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Data;
import com.axellience.vuegwt.core.annotations.component.Prop;
import com.axellience.vuegwt.core.annotations.component.Watch;
import com.axellience.vuegwt.core.client.component.IsVueComponent;
import com.axellience.vuegwt.core.client.component.hooks.HasCreated;
import com.google.web.bindery.event.shared.EventBus;

import nl.yogh.wui.component.notification.NotificationComponent;
import nl.yogh.wui.explorer.context.ConfigurationContext;

@Component(components = {
    NotificationComponent.class
})
public class ApplicationView implements IsVueComponent, HasCreated {
  @Prop EventBus eventBus;

  @Data @Inject ConfigurationContext context;

  @Data String source = "blockstream";

  @Watch(value = "source", isImmediate = true)
  public void onSourceChange() {
    context.setSource(source);
  }

  @Override
  public void created() {
    source = context.getSource();
  }
}
