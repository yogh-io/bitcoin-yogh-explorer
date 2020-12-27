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
import nl.yogh.wui.explorer.command.LoadAddressCommand;
import nl.yogh.wui.explorer.command.SourceChangedCommand;
import nl.yogh.wui.explorer.context.AddressContext;
import nl.yogh.wui.explorer.place.AddressPlace;
import nl.yogh.wui.explorer.service.ElectrServiceAsync;
import nl.yogh.wui.explorer.service.domain.AddressInformation;
import nl.yogh.wui.explorer.service.domain.UtxoInformation;

public class AddressDaemon extends BasicEventComponent implements Daemon {
  private static final BlockDaemonEventBinder EVENT_BINDER = GWT.create(BlockDaemonEventBinder.class);

  interface BlockDaemonEventBinder extends EventBinder<AddressDaemon> {}

  @Inject ElectrServiceAsync service;

  @Inject AddressContext context;

  @Inject PlaceController placeController;

  private String latestFetch;

  @EventHandler
  public void onPlaceChangeCommand(final PlaceChangeCommand c) {
    if (c.getValue() instanceof AddressPlace) {
      return;
    }

    latestFetch = null;
  }

  @EventHandler
  public void onSourceChangedCommand(final SourceChangedCommand c) {
    latestFetch = null;
  }

  @EventHandler
  public void onLoadAddressCommand(final LoadAddressCommand c) {
    final String address = c.getValue();

    if (address.equals(latestFetch)) {
      return;
    }

    latestFetch = address;
    context.clear();
    context.setLoading();

    service.fetchAddress(address, AppAsyncCallback.create(
        v -> ifMatchThen(address, () -> loadAddressInformation(v)),
        e -> ifMatchThen(address, () -> failAddressInformation(e))));
    service.fetchUtxos(address, AppAsyncCallback.create(
        v -> ifMatchThen(address, () -> loadUtxosInformation(v)),
        e -> ifMatchThen(address, () -> failUtxosInformation(e))));

    placeController.goTo(new AddressPlace(address));
  }

  private void loadUtxosInformation(final UtxoInformation[] v) {
    context.setUtxos(v);
  }

  private void failAddressInformation(final Throwable e) {
    context.setFailure(e);
  }

  private void failUtxosInformation(final Throwable e) {
    // Depend on failAddressInformation instead
  }

  private void loadAddressInformation(final AddressInformation v) {
    context.setAddressInformation(v);
  }

  private void ifMatchThen(final String hash, final Runnable runnable) {
    if (hash.equals(latestFetch)) {
      runnable.run();
    }
  }

  @Override
  public void setEventBus(final EventBus eventBus) {
    super.setEventBus(eventBus, this, EVENT_BINDER);
  }
}
