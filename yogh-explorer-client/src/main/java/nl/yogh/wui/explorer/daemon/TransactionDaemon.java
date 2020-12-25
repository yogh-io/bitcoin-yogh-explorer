package nl.yogh.wui.explorer.daemon;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import nl.aerius.wui.command.PlaceChangeCommand;
import nl.aerius.wui.event.BasicEventComponent;
import nl.aerius.wui.future.AppAsyncCallback;
import nl.aerius.wui.place.PlaceController;
import nl.yogh.wui.explorer.component.links.LoadTransactionCommand;
import nl.yogh.wui.explorer.context.TransactionContext;
import nl.yogh.wui.explorer.place.TransactionPlace;
import nl.yogh.wui.explorer.service.ElectrServiceAsync;
import nl.yogh.wui.explorer.service.domain.TransactionInformation;

public class TransactionDaemon extends BasicEventComponent implements Daemon {
  private static final BlockDaemonEventBinder EVENT_BINDER = GWT.create(BlockDaemonEventBinder.class);

  interface BlockDaemonEventBinder extends EventBinder<TransactionDaemon> {}

  @Inject ElectrServiceAsync service;

  @Inject TransactionContext context;

  @Inject PlaceController placeController;

  private String latestFetch;

  @EventHandler
  public void onPlaceChangeCommand(final PlaceChangeCommand c) {
    if (c.getValue() instanceof TransactionPlace) {
      return;
    }

    latestFetch = null;
  }

  @EventHandler
  public void onLoadTransactionCommand(final LoadTransactionCommand c) {
    final String hash = c.getValue();

    if (hash.equals(latestFetch)) {
      return;
    }

    latestFetch = hash;
    context.clear();
    context.setLoading();

    service.fetchTransaction(hash, AppAsyncCallback.create(
        v -> ifMatchThen(hash, () -> loadTransactionInformation(v)),
        e -> ifMatchThen(hash, () -> failTransactionInformation(e))));

    placeController.goTo(new TransactionPlace(hash));
  }

  private void ifMatchThen(final String hash, final Runnable runnable) {
    if (hash.equals(latestFetch)) {
      runnable.run();
    }
  }

  private void loadTransactionInformation(final TransactionInformation transactionInformation) {
    context.setTransactionInformation(transactionInformation);
  }

  private void failTransactionInformation(final Throwable e) {
    context.setFailure(e);
  }

  @Override
  public void setEventBus(final EventBus eventBus) {
    super.setEventBus(eventBus, this, EVENT_BINDER);
  }
}
