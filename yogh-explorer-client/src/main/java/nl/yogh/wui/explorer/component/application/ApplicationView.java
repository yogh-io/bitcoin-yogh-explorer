package nl.yogh.wui.explorer.component.application;

import javax.inject.Inject;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Data;
import com.axellience.vuegwt.core.annotations.component.Prop;
import com.axellience.vuegwt.core.annotations.component.Watch;
import com.axellience.vuegwt.core.client.component.IsVueComponent;
import com.axellience.vuegwt.core.client.component.hooks.HasCreated;
import com.google.web.bindery.event.shared.EventBus;

import jsinterop.annotations.JsMethod;

import nl.aerius.wui.place.PlaceController;
import nl.yogh.wui.component.notification.NotificationComponent;
import nl.yogh.wui.explorer.command.SourceChangedCommand;
import nl.yogh.wui.explorer.config.EnvironmentConfiguration;
import nl.yogh.wui.explorer.context.ConfigurationContext;
import nl.yogh.wui.explorer.place.ExplorerPlaces.LandingPlace;

@Component(components = {
    NotificationComponent.class
})
public class ApplicationView implements IsVueComponent, HasCreated {
  @Prop EventBus eventBus;

  @Data @Inject EnvironmentConfiguration cfg;

  @Data @Inject ConfigurationContext context;

  @Inject PlaceController placeController;

  @Data String source = "blockstream";

  @Watch(value = "source", isImmediate = true)
  public void onSourceChange() {
    context.setSource(source);
    eventBus.fireEvent(new SourceChangedCommand());
  }

  @JsMethod
  public void goHome() {
    placeController.goTo(new LandingPlace());
  }

  @Override
  public void created() {
    source = context.getSource();
  }
}
