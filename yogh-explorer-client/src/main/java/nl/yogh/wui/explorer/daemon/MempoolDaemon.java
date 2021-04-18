package nl.yogh.wui.explorer.daemon;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import nl.aerius.wui.command.PlaceChangeCommand;
import nl.aerius.wui.event.BasicEventComponent;
import nl.aerius.wui.place.PlaceController;
import nl.yogh.wui.explorer.command.LoadMempoolCommand;
import nl.yogh.wui.explorer.command.SourceChangedCommand;
import nl.yogh.wui.explorer.context.MempoolContext;
import nl.yogh.wui.explorer.place.places.MempoolPlace;
import nl.yogh.wui.explorer.service.ElectrServiceAsync;

public class MempoolDaemon extends BasicEventComponent implements Daemon {
  private static final MempoolDaemonEventBinder EVENT_BINDER = GWT.create(MempoolDaemonEventBinder.class);

  interface MempoolDaemonEventBinder extends EventBinder<MempoolDaemon> {}

  @Inject ElectrServiceAsync service;

  @Inject MempoolContext context;

  @Inject PlaceController placeController;

  @EventHandler
  public void onPlaceChangeCommand(final PlaceChangeCommand c) {
    if (c.getValue() instanceof MempoolPlace) {
      // Activate
    } else {
      // Deactivate
    }
  }

  @EventHandler
  public void onSourceChangedCommand(final SourceChangedCommand c) {
    context.clear();
  }

  @EventHandler
  public void onLoadMempoolCommand(final LoadMempoolCommand c) {
    context.clear();
    context.setLoading();


    placeController.goTo(new MempoolPlace());
  }

  @Override
  public void setEventBus(final EventBus eventBus) {
    super.setEventBus(eventBus, this, EVENT_BINDER);
  }
}
