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
import nl.yogh.wui.explorer.command.LoadBlockCommand;
import nl.yogh.wui.explorer.command.SourceChangedCommand;
import nl.yogh.wui.explorer.context.BlockContext;
import nl.yogh.wui.explorer.daemon.util.DelayingStatefulDaemon;
import nl.yogh.wui.explorer.place.BlockPlace;
import nl.yogh.wui.explorer.service.ElectrServiceAsync;
import nl.yogh.wui.explorer.service.domain.BlockInformation;

public class BlockDaemon extends DelayingStatefulDaemon<String> implements Daemon {
  private static final BlockDaemonEventBinder EVENT_BINDER = GWT.create(BlockDaemonEventBinder.class);

  interface BlockDaemonEventBinder extends EventBinder<BlockDaemon> {}

  @Inject ElectrServiceAsync service;

  @Inject BlockContext context;

  @Inject PlaceController placeController;

  @EventHandler
  public void onNotifyHistoryEvent(final NotifyHistoryEvent e) {
    if (e.getValue() instanceof BlockPlace) {
      eventBus.fireEvent(new LoadBlockCommand(((BlockPlace) e.getValue()).getHash()));
    }
  }

  @EventHandler
  public void onPlaceChangeCommand(final PlaceChangeCommand c) {
    if (c.getValue() instanceof BlockPlace) {
      return;
    }

    resetState();
  }

  @EventHandler
  public void onSourceChangedCommand(final SourceChangedCommand c) {
    if (state == null) {
      return;
    }

    final String hash = state;
    resetState();
    eventBus.fireEvent(new LoadBlockCommand(hash));
  }

  @EventHandler
  public void onLoadBlockCommand(final LoadBlockCommand c) {
    if (state == null) {
      context.setLoading();
    }

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
