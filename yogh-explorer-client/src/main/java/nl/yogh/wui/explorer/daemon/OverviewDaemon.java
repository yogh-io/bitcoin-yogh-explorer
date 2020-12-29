package nl.yogh.wui.explorer.daemon;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;
import com.google.web.bindery.event.shared.binder.GenericEvent;

import nl.aerius.wui.event.BasicEventComponent;
import nl.aerius.wui.future.AppAsyncCallback;
import nl.yogh.wui.explorer.command.ActivateOverviewCommand;
import nl.yogh.wui.explorer.command.DeactivateOverviewCommand;
import nl.yogh.wui.explorer.command.SourceChangedCommand;
import nl.yogh.wui.explorer.command.UpdateRecentBlocksCommand;
import nl.yogh.wui.explorer.context.OverviewContext;
import nl.yogh.wui.explorer.service.ElectrServiceAsync;

public class OverviewDaemon extends BasicEventComponent implements Daemon {
  private static final OverviewDaemonEventBinder EVENT_BINDER = GWT.create(OverviewDaemonEventBinder.class);

  private static final int DELAY = 15 * 1000;

  interface OverviewDaemonEventBinder extends EventBinder<OverviewDaemon> {}

  @Inject ElectrServiceAsync service;

  @Inject OverviewContext context;

  private final Timer timer = new Timer() {
    @Override
    public void run() {
      fetchMempool();
      fetchRecentTransactions();
    };
  };

  @EventHandler(handles = { SourceChangedCommand.class, ActivateOverviewCommand.class })
  public void onSourceChangedCommand(final GenericEvent c) {
    context.clear();
    context.setLoading();

    fetchRecentBlocks();
    fetchMempool();
    fetchRecentTransactions();

    // Reset timer
    timer.scheduleRepeating(DELAY);
  }

  @EventHandler
  public void onUpdateRecentBlocksCommand(final UpdateRecentBlocksCommand c) {
    fetchRecentBlocks();
  }

  private void fetchRecentBlocks() {
    service.fetchRecentBlocks(AppAsyncCallback.create(
        v -> context.setRecentBlocks(v),
        e -> context.setFailure(e)));
  }

  private void fetchMempool() {
    service.fetchMempool(AppAsyncCallback.create(
        v -> context.setMempool(v),
        e -> {}));
  }

  private void fetchRecentTransactions() {
    service.fetchRecentTransactions(AppAsyncCallback.create(
        v -> context.setRecentTransactions(v),
        e -> {}));
  }

  @EventHandler
  public void onDeactivateOverviewCommand(final DeactivateOverviewCommand c) {
    timer.cancel();
  }

  @Override
  public void setEventBus(final EventBus eventBus) {
    super.setEventBus(eventBus, this, EVENT_BINDER);
  }
}
