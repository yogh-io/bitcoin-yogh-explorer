package nl.yogh.wui.explorer.daemon;

import java.util.List;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import elemental2.core.JsArray;

import nl.aerius.wui.command.PlaceChangeCommand;
import nl.aerius.wui.event.NotifyHistoryEvent;
import nl.aerius.wui.future.AppAsyncCallback;
import nl.aerius.wui.place.PlaceController;
import nl.yogh.wui.explorer.command.LoadBlockCommand;
import nl.yogh.wui.explorer.command.LoadBlockHeightCommand;
import nl.yogh.wui.explorer.command.SourceChangedCommand;
import nl.yogh.wui.explorer.context.BlockContext;
import nl.yogh.wui.explorer.daemon.util.DelayingStatefulDaemon;
import nl.yogh.wui.explorer.place.places.BlockHeightPlace;
import nl.yogh.wui.explorer.place.places.BlockPlace;
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
    } else if (e.getValue() instanceof BlockHeightPlace) {
      eventBus.fireEvent(new LoadBlockHeightCommand(((BlockHeightPlace) e.getValue()).getHeight()));
    }
  }

  @EventHandler
  public void onPlaceChangeCommand(final PlaceChangeCommand c) {
    if (c.getValue() instanceof BlockPlace
        || c.getValue() instanceof BlockHeightPlace) {
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

  private boolean initLoad(final String loadState) {
    if (loadState.equals(state)) {
      return false;
    }

    setState(loadState);
    context.softClear();
    context.setLoading();
    delayedClear(() -> {
      context.clear();
    });

    return true;
  }

  @EventHandler
  public void onLoadBlockHeightCommand(final LoadBlockHeightCommand c) {
    final String height = c.getValue();
    if (!initLoad(height)) {
      return;
    }

    service.fetchBlockAtHeight(height, AppAsyncCallback.create(
        v -> ifMatchThen(height, () -> loadBlock(v)),
        e -> ifMatchThen(height, () -> failBlockInformation(e))));

    placeController.goTo(new BlockHeightPlace(c.getValue()));
  }

  private void loadBlock(final String hash) {
    setState(hash);
    service.fetchBlock(hash, AppAsyncCallback.create(
        v -> ifMatchThen(hash, () -> loadBlockInformation(v)),
        e -> ifMatchThen(hash, () -> failBlockInformation(e))));
    service.fetchRawBlock(hash, AppAsyncCallback.create(
        v -> ifMatchThen(hash, () -> loadRawBlock(v)),
        e -> ifMatchThen(hash, () -> failRawBlock(e))));
    service.fetchTxids(hash, AppAsyncCallback.create(
        v -> ifMatchThen(hash, () -> loadTxids(hash, v)),
        e -> ifMatchThen(hash, () -> failTxids(e))));
  }

  @EventHandler
  public void onLoadBlockCommand(final LoadBlockCommand c) {
    final String hash = c.getValue();
    if (!initLoad(hash)) {
      return;
    }

    loadBlock(hash);

    placeController.goTo(new BlockPlace(hash));
  }

  private void loadBlockInformation(final BlockInformation blockInformation) {
    context.setBlockInformation(blockInformation);
  }

  private void loadRawBlock(final String raw) {
    context.setRawBlock(raw);
  }

  private void loadTxids(final String hash, final JsArray<String> txids) {
    final List<String> lst = txids.asList();
    context.setTxids(lst);

    final String coinbaseTxid = lst.get(0);
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
