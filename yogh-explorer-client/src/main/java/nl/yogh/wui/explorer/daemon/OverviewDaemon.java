package nl.yogh.wui.explorer.daemon;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

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

  private boolean active;

  @EventHandler
  public void onSourceChangedCommand(final SourceChangedCommand c) {
    if (!active) {
      return;
    }

    refresh();
  }

  private void refresh() {
    context.clear();
    context.setLoading();

    fetchRecentBlocks();
    fetchMempool();
    fetchRecentTransactions();
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
    context.setTransactionsLoading();
    service.fetchRecentTransactions(AppAsyncCallback.create(
        v -> context.setRecentTransactions(v),
        e -> {}));
  }

  @EventHandler
  public void onActivateOverviewCommand(final ActivateOverviewCommand c) {
    refresh();
    timer.cancel();
    timer.scheduleRepeating(DELAY);
    active = true;
  }

  @EventHandler
  public void onDeactivateOverviewCommand(final DeactivateOverviewCommand c) {
    timer.cancel();
    active = false;
  }

  @Override
  public void setEventBus(final EventBus eventBus) {
    super.setEventBus(eventBus, this, EVENT_BINDER);
  }
}
