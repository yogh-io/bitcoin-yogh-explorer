package nl.yogh.wui.explorer.daemon;

import java.util.Arrays;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import nl.aerius.wui.event.BasicEventComponent;
import nl.yogh.wui.explorer.command.UpdateRecentBlocksCommand;
import nl.yogh.wui.explorer.context.OverviewContext;
import nl.yogh.wui.explorer.service.ConversionAsyncCallback;
import nl.yogh.wui.explorer.service.ElectrServiceAsync;

public class OverviewDaemon extends BasicEventComponent implements Daemon {
  private static final OverviewDaemonEventBinder EVENT_BINDER = GWT.create(OverviewDaemonEventBinder.class);

  interface OverviewDaemonEventBinder extends EventBinder<OverviewDaemon> {}

  @Inject ElectrServiceAsync service;

  @Inject OverviewContext context;

  @EventHandler
  public void onUpdateRecentBlocksCommand(final UpdateRecentBlocksCommand c) {
    context.clear();

    service.fetchRecentBlocks(ConversionAsyncCallback.create(
        v -> Arrays.asList(v),
        v -> context.setRecentBlocks(v),
        e -> context.setFailure(e)));
  }

  @Override
  public void setEventBus(final EventBus eventBus) {
    super.setEventBus(eventBus, this, EVENT_BINDER);
  }
}
