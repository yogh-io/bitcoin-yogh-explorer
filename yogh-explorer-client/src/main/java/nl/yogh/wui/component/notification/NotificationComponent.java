/*
 * Copyright the State of the Netherlands
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */
package nl.yogh.wui.component.notification;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Data;
import com.axellience.vuegwt.core.client.component.IsVueComponent;
import com.axellience.vuegwt.core.client.component.hooks.HasCreated;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.ExtraSimpleHtmlSanitizer;
import com.google.gwt.user.client.Timer;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;

import nl.aerius.wui.event.NotificationEvent;
import nl.aerius.wui.util.Notification;
import nl.yogh.wui.component.FadeTransition;
import nl.yogh.wui.component.VerticalCollapseGroup;
import nl.yogh.wui.explorer.command.ToggleNotificationDisplayCommand;
import nl.yogh.wui.explorer.event.ToggleNotificationDisplayEvent;

@Component(name = "app-notification", components = {
    VerticalCollapseGroup.class,
    FadeTransition.class
})
public class NotificationComponent implements IsVueComponent, HasCreated {
  private static final int REMOVE_DELAY = 12000;

  private final NotificationComponentEventBinder EVENT_BINDER = GWT.create(NotificationComponentEventBinder.class);

  interface NotificationComponentEventBinder extends EventBinder<NotificationComponent> {}

  @Inject EventBus eventBus;

  @Data boolean showing;
  @Data boolean tempShowing;

  @JsProperty @Data List<Notification> notifications = new ArrayList<>();

  private Timer tempVisibleTimer;

  @EventHandler
  public void onToggleNotificationDisplayCommand(final ToggleNotificationDisplayCommand c) {
    showing = !showing && !tempShowing;
    if (!showing && tempShowing) {
      tempShowing = false;
    }

    c.setToggleInfo(new NotificationToggleInfo(showing, notifications.size()));
  }

  @EventHandler
  public void onNotificationEvent(final NotificationEvent e) {
    notifications.add(0, e.getValue());

    tempShowing = true;
    eventBus.fireEvent(new ToggleNotificationDisplayEvent(new NotificationToggleInfo(true, notifications.size())));
    tempVisibleTimer.schedule(REMOVE_DELAY);
  }

  @JsMethod
  public String sanitize(final String html) {
    return ExtraSimpleHtmlSanitizer.sanitizeHtml(html).asString();
  }

  private void endTemporaryVisiblePeriod() {
    tempShowing = false;
    eventBus.fireEvent(new ToggleNotificationDisplayEvent(new NotificationToggleInfo(false, notifications.size())));
  }

  @JsMethod
  public void remove(final Notification notification) {
    notifications.remove(notification);
    eventBus.fireEvent(new ToggleNotificationDisplayEvent(new NotificationToggleInfo(showing, notifications.size())));
  }

  @Override
  public void created() {
    EVENT_BINDER.bindEventHandlers(this, eventBus);
    tempVisibleTimer = new Timer() {
      @Override
      public void run() {
        endTemporaryVisiblePeriod();
      }
    };
  }
}
