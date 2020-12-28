package nl.yogh.wui.explorer.daemon;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import nl.aerius.wui.event.BasicEventComponent;
import nl.aerius.wui.future.AppAsyncCallback;
import nl.aerius.wui.place.PlaceController;
import nl.aerius.wui.util.NotificationUtil;
import nl.yogh.wui.explorer.command.SourceChangedCommand;
import nl.yogh.wui.explorer.context.BlockContext;
import nl.yogh.wui.explorer.service.ElectrServiceAsync;
import nl.yogh.wui.util.EllipsisUtil;

public class BlockchainDaemon extends BasicEventComponent implements Daemon {
  private static final BlockchainDaemonEventBinder EVENT_BINDER = GWT.create(BlockchainDaemonEventBinder.class);

  interface BlockchainDaemonEventBinder extends EventBinder<BlockchainDaemon> {}

  private static final int CHECK_TIP_DELAY = 15 * 1000;

  @Inject ElectrServiceAsync service;

  @Inject PlaceController placeController;

  @Inject BlockContext context;

  private String latestBlock;

  private final Timer timer = new Timer() {
    @Override
    public void run() {
      checkTip();
    }
  };

  @EventHandler
  public void onSourceChangedCommand(final SourceChangedCommand c) {
    checkTip();
  }

  private void init() {
    timer.scheduleRepeating(CHECK_TIP_DELAY);
    checkTip();
  }

  private void checkTip() {
    service.fetchTip(AppAsyncCallback.create(
        v -> ifNotMatchThen(v, () -> updateBlock(v)),
        e -> failBlock(e)));
  }

  private void failBlock(final Throwable e) {
    NotificationUtil.broadcastWarning(eventBus, "Could not fetch tip: " + e.getMessage());
  }

  private void updateBlock(final String hash) {
    service.fetchBlock(hash, block -> {
      if (context.tip != null) {
        NotificationUtil.broadcastMessage(eventBus, "New block: " + EllipsisUtil.applyInner(block.id) + " at height " + block.height);
      }

      latestBlock = block.id;
      context.setTip(block);
    });
  }

  private void ifNotMatchThen(final String hash, final Runnable runnable) {
    if (!hash.equals(latestBlock)) {
      runnable.run();
    }
  }

  @Override
  public void setEventBus(final EventBus eventBus) {
    super.setEventBus(eventBus, this, EVENT_BINDER);

    init();
  }
}
