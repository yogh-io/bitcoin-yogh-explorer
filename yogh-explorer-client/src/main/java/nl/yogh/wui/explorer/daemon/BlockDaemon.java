package nl.yogh.wui.explorer.daemon;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import nl.aerius.wui.command.PlaceChangeCommand;
import nl.aerius.wui.dev.GWTProd;
import nl.aerius.wui.future.AppAsyncCallback;
import nl.aerius.wui.place.PlaceController;
import nl.yogh.wui.explorer.command.LoadBlockCommand;
import nl.yogh.wui.explorer.command.SourceChangedCommand;
import nl.yogh.wui.explorer.context.BlockContext;
import nl.yogh.wui.explorer.place.BlockPlace;
import nl.yogh.wui.explorer.service.ElectrServiceAsync;
import nl.yogh.wui.explorer.service.domain.BlockInformation;

public class BlockDaemon extends SimpleStatefulDaemon<String> implements Daemon {
  private static final BlockDaemonEventBinder EVENT_BINDER = GWT.create(BlockDaemonEventBinder.class);

  interface BlockDaemonEventBinder extends EventBinder<BlockDaemon> {}

  @Inject ElectrServiceAsync service;

  @Inject BlockContext context;

  @Inject PlaceController placeController;

  @EventHandler
  public void onPlaceChangeCommand(final PlaceChangeCommand c) {
    if (c.getValue() instanceof BlockPlace) {
      return;
    }

    resetState();
  }

  @EventHandler
  public void onSourceChangedCommand(final SourceChangedCommand c) {
    resetState();
  }

  @EventHandler
  public void onLoadBlockCommand(final LoadBlockCommand c) {
    final String hash = c.getValue();
    if (hash.equals(state)) {
      return;
    }

    setState(hash);
    context.softClear();
    delayedLoad(() -> {
      context.clear();
      context.setLoading();
    });

    service.fetchBlock(hash, AppAsyncCallback.create(
        v -> ifMatchThen(hash, () -> loadBlockInformation(v)),
        e -> ifMatchThen(hash, () -> failBlockInformation(e))));
    service.fetchRawBlock(hash, AppAsyncCallback.create(
        v -> ifMatchThen(hash, () -> loadRawBlock(v)),
        e -> ifMatchThen(hash, () -> failRawBlock(e))));
    service.fetchTxids(hash, AppAsyncCallback.create(
        v -> ifMatchThen(hash, () -> loadTxids(hash, v)),
        e -> ifMatchThen(hash, () -> failTxids(e))));

    placeController.goTo(new BlockPlace(hash));
  }

  private void loadBlockInformation(final BlockInformation blockInformation) {
    context.setBlockInformation(blockInformation);
  }

  private void loadRawBlock(final String raw) {
    context.setRawBlock(raw);
  }

  private void loadTxids(final String hash, final String[] txids) {
    context.setTxids(txids);

    final String coinbaseTxid = txids[0];
    service.fetchTransactionHex(coinbaseTxid, AppAsyncCallback.create(
        v -> ifMatchThen(hash, () -> loadCoinbaseTransaction(v)),
        e -> ifMatchThen(hash, () -> failCoinbaseTransaction(e))));
  }

  private void loadCoinbaseTransaction(final String coinbaseHex) {
    context.setRawCoinbase(coinbaseHex);
  }

  private void failBlockInformation(final Throwable e) {
    e.printStackTrace();
    GWTProd.log(e);
    GWTProd.log("Failing.. " + e.getMessage());
    context.setFailure(e);
  }

  private void failRawBlock(final Throwable e) {
    // Do nothing, depending on block information failure clause instead
  }

  private void failTxids(final Throwable e) {
    // Do nothing, depending on block information failure clause instead
  }

  private void failCoinbaseTransaction(final Throwable e) {
    // Do nothing, depending on block information failure clause instead
  }

  @Override
  public void setEventBus(final EventBus eventBus) {
    super.setEventBus(eventBus, this, EVENT_BINDER);
  }
}
