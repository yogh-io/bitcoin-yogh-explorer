package nl.yogh.wui.explorer.dev;

import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;
import com.google.web.bindery.event.shared.binder.GenericEvent;

import nl.aerius.wui.command.PlaceChangeCommand;
import nl.aerius.wui.command.PlaceChangeRequestCommand;
import nl.aerius.wui.command.SimpleGenericCommand;
import nl.aerius.wui.dev.DevelopmentObserver;
import nl.aerius.wui.dev.GWTProd;
import nl.aerius.wui.event.BasicEventComponent;
import nl.aerius.wui.event.NotificationEvent;
import nl.aerius.wui.event.PlaceChangeEvent;
import nl.aerius.wui.event.SimpleGenericEvent;

@Singleton
public class ExplorerDevelopmentObserver extends BasicEventComponent implements DevelopmentObserver {
  interface DevelopmentObserverMonitorImplEventBinder extends EventBinder<ExplorerDevelopmentObserver> {}

  private final DevelopmentObserverMonitorImplEventBinder EVENT_BINDER = GWT.create(DevelopmentObserverMonitorImplEventBinder.class);

  @Inject
  public ExplorerDevelopmentObserver() {}

  @EventHandler(handles = { PlaceChangeCommand.class })
  public void onSimpleGenericCommand(@SuppressWarnings("rawtypes") final SimpleGenericCommand e) {
    log(e.getClass().getSimpleName(), e.getValue());
  }

  // @EventHandler(handles = {})
  public void onSimpleGenericCommand(final GenericEvent e) {
    log(e.getClass().getSimpleName());
  }

  @EventHandler(handles = { NotificationEvent.class, PlaceChangeEvent.class, PlaceChangeRequestCommand.class })
  public void onSimpleGenericEvent(@SuppressWarnings("rawtypes") final SimpleGenericEvent e) {
    log(e.getClass().getSimpleName(), e.getValue());
    brbr();
  }

  private void brbr() {
    logRaw("");
  }

  private void log(final String origin) {
    logRaw("[" + origin + "]");
  }

  private void log(final String origin, final Object val) {
    logRaw("[" + origin + "] " + String.valueOf(val));
  }

  private void logRaw(final String string) {
    GWTProd.log(string);
  }

  @Override
  public void setEventBus(final EventBus eventBus) {
    super.setEventBus(eventBus, this, EVENT_BINDER);
  }
}
