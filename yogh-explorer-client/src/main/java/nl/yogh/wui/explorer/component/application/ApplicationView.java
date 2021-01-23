package nl.yogh.wui.explorer.component.application;

import javax.inject.Inject;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Computed;
import com.axellience.vuegwt.core.annotations.component.Data;
import com.axellience.vuegwt.core.annotations.component.Prop;
import com.axellience.vuegwt.core.client.component.IsVueComponent;
import com.google.web.bindery.event.shared.EventBus;

import jsinterop.annotations.JsMethod;

import nl.yogh.wui.component.notification.NotificationComponent;
import nl.yogh.wui.explorer.command.ToggleNotificationDisplayCommand;
import nl.yogh.wui.explorer.config.EnvironmentConfiguration;

@Component(components = {
    ApplicationHeader.class,
    NotificationComponent.class
})
public class ApplicationView implements IsVueComponent {
  @Prop EventBus eventBus;

  @Data @Inject EnvironmentConfiguration cfg;

  @Computed
  public String getApplicationBuild() {
    return "https://github.com/yogh-io/bitcoin-yogh-explorer/commit/" + cfg.getBuildNumber();
  }

  @JsMethod
  public void toggleNotifications() {
    eventBus.fireEvent(new ToggleNotificationDisplayCommand());
  }
}
