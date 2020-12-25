package nl.yogh.wui.explorer.daemon;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import nl.aerius.wui.command.PlaceChangeCommand;
import nl.aerius.wui.event.BasicEventComponent;
import nl.aerius.wui.future.AppAsyncCallback;
import nl.aerius.wui.place.PlaceController;
import nl.yogh.wui.explorer.command.LoadBlockCommand;
import nl.yogh.wui.explorer.context.BlockContext;
import nl.yogh.wui.explorer.place.BlockPlace;
import nl.yogh.wui.explorer.service.ConversionAsyncCallback;
import nl.yogh.wui.explorer.service.ElectrServiceAsync;
import nl.yogh.wui.explorer.service.domain.BlockInformation;

public class BlockDaemon extends BasicEventComponent implements Daemon {
  private static final BlockDaemonEventBinder EVENT_BINDER = GWT.create(BlockDaemonEventBinder.class);

  interface BlockDaemonEventBinder extends EventBinder<BlockDaemon> {}

  @Inject ElectrServiceAsync service;

  @Inject BlockContext context;

  @Inject PlaceController placeController;

  private String latestFetch;

  @EventHandler
  public void onPlaceChangeCommand(final PlaceChangeCommand c) {
    if (c.getValue() instanceof BlockPlace) {
      return;
    }

    latestFetch = null;
  }

  @EventHandler
  public void onLoadBlockCommand(final LoadBlockCommand c) {
    final String hash = c.getValue();

    if (hash.equals(latestFetch)) {
      return;
    }

    latestFetch = hash;
    context.clear();
    context.setLoading();

    service.fetchBlock(hash, AppAsyncCallback.create(
        v -> ifMatchThen(hash, () -> loadBlockInformation(v)),
        e -> ifMatchThen(hash, () -> failBlockInformation(e))));
    service.fetchRawBlock(hash, AppAsyncCallback.create(
        v -> ifMatchThen(hash, () -> loadRawBlock(v)),
        e -> ifMatchThen(hash, () -> failRawBlock(e))));
    service.fetchTxids(hash, ConversionAsyncCallback.create(
        v -> Arrays.asList(v),
        v -> ifMatchThen(hash, () -> loadTxids(v)),
        e -> ifMatchThen(hash, () -> failTxids(e))));

    placeController.goTo(new BlockPlace(hash));
  }

  private void ifMatchThen(final String hash, final Runnable runnable) {
    if (hash.equals(latestFetch)) {
      runnable.run();
    }
  }

  private void loadBlockInformation(final BlockInformation blockInformation) {
    context.setBlockInformation(blockInformation);
  }

  private void loadRawBlock(final String raw) {
    context.setRawBlock(raw);
  }

  private void loadTxids(final List<String> txids) {
    context.setTxids(txids);
  }

  private void failBlockInformation(final Throwable e) {
    context.setFailure(e);
  }

  private void failRawBlock(final Throwable e) {}

  private void failTxids(final Throwable e) {
    // Do nothing, depending on block information failure clause instead
  }

  @Override
  public void setEventBus(final EventBus eventBus) {
    super.setEventBus(eventBus, this, EVENT_BINDER);
  }
}