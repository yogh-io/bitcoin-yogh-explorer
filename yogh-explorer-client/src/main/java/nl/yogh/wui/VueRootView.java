package nl.yogh.wui;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Data;
import com.axellience.vuegwt.core.client.component.IsVueComponent;
import com.axellience.vuegwt.core.client.vue.VueComponentFactory;
import com.google.web.bindery.event.shared.EventBus;

import nl.aerius.wui.event.HasEventBus;
import nl.aerius.wui.vue.AcceptsOneComponent;
import nl.yogh.wui.explorer.ui.MainView;

@Component(components = {
    MainView.class
})
public class VueRootView implements IsVueComponent, AcceptsOneComponent, HasEventBus {
  @Data String id;
  @Data Object presenter;

  @Data EventBus eventBus;

  @Override
  public <P> void setComponent(final VueComponentFactory<?> factory, final P presenter) {
    id = factory.getComponentTagName();
    this.presenter = presenter;
  }

  @Override
  public void setEventBus(final EventBus eventBus) {
    this.eventBus = eventBus;
  }
}
