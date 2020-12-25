package nl.yogh.wui.explorer.daemon;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;

import nl.aerius.wui.event.BasicEventComponent;
import nl.aerius.wui.place.PlaceController;
import nl.yogh.wui.explorer.context.BlockContext;
import nl.yogh.wui.explorer.service.ElectrServiceAsync;

public class BlockchainDaemon extends BasicEventComponent implements Daemon {
  private static final BlockDaemonEventBinder EVENT_BINDER = GWT.create(BlockDaemonEventBinder.class);

  interface BlockDaemonEventBinder extends EventBinder<BlockchainDaemon> {}

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

  private void init() {
    timer.scheduleRepeating(CHECK_TIP_DELAY);
    checkTip();
  }

  private void checkTip() {
    service.fetchTip(v -> ifNotMatchThen(v, () -> updateBlock(v)));
  }

  private void updateBlock(final String hash) {
    service.fetchBlock(hash, block -> context.setTip(block));
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
