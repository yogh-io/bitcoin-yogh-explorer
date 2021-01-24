package nl.yogh.wui.explorer.daemon;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import nl.aerius.wui.command.PlaceChangeCommand;
import nl.aerius.wui.event.NotifyHistoryEvent;
import nl.aerius.wui.future.AppAsyncCallback;
import nl.aerius.wui.place.PlaceController;
import nl.yogh.wui.explorer.command.SourceChangedCommand;
import nl.yogh.wui.explorer.component.links.LoadTransactionCommand;
import nl.yogh.wui.explorer.context.TransactionContext;
import nl.yogh.wui.explorer.daemon.util.DelayingStatefulDaemon;
import nl.yogh.wui.explorer.place.places.TransactionPlace;
import nl.yogh.wui.explorer.service.ElectrServiceAsync;
import nl.yogh.wui.explorer.service.domain.TransactionInformation;

public class TransactionDaemon extends DelayingStatefulDaemon<String> implements Daemon {
  private static final BlockDaemonEventBinder EVENT_BINDER = GWT.create(BlockDaemonEventBinder.class);

  interface BlockDaemonEventBinder extends EventBinder<TransactionDaemon> {}

  @Inject ElectrServiceAsync service;

  @Inject TransactionContext context;

  @Inject PlaceController placeController;

  @EventHandler
  public void onNotifyHistoryEvent(final NotifyHistoryEvent e) {
    if (e.getValue() instanceof TransactionPlace) {
      eventBus.fireEvent(new LoadTransactionCommand(((TransactionPlace) e.getValue()).getHash()));
    }
  }

  @EventHandler
  public void onPlaceChangeCommand(final PlaceChangeCommand c) {
    if (c.getValue() instanceof TransactionPlace) {
      return;
    }

    resetState();
  }

  @EventHandler
  public void onSourceChangedCommand(final SourceChangedCommand c) {
    resetState();
  }

  @EventHandler
  public void onLoadTransactionCommand(final LoadTransactionCommand c) {
    final String hash = c.getValue();

    if (hash.equals(state)) {
      return;
    }

    setState(hash);
    context.softClear();
    context.setLoading();
    delayedClear(() -> {
      context.clear();
    });

    service.fetchTransaction(hash, AppAsyncCallback.create(
        v -> ifMatchThen(hash, () -> loadTransactionInformation(v)),
        e -> ifMatchThen(hash, () -> failTransactionInformation(e))));
    service.fetchTransactionHex(hash, AppAsyncCallback.create(
        v -> ifMatchThen(hash, () -> loadRawTransaction(v)),
        e -> ifMatchThen(hash, () -> failRawTransaction(e))));

    placeController.goTo(new TransactionPlace(hash));
  }

  private void loadRawTransaction(final String hex) {
    context.setRawTransaction(hex);
  }

  private void loadTransactionInformation(final TransactionInformation transactionInformation) {
    context.setTransactionInformation(transactionInformation);
  }

  private void failTransactionInformation(final Throwable e) {
    context.setFailure(e);
  }

  private void failRawTransaction(final Throwable e) {
    // Depend on failTransactionInformation() instead.
  }

  @Override
  public void setEventBus(final EventBus eventBus) {
    super.setEventBus(eventBus, this, EVENT_BINDER);
  }
}
