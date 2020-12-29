package nl.yogh.wui.explorer.daemon.util;

import nl.aerius.wui.event.BasicEventComponent;
import nl.aerius.wui.util.SchedulerUtil;

public abstract class DelayingStatefulDaemon<T> extends BasicEventComponent {
  private static final int DEFAULT_TIMEOUT = 250;

  protected T state;
  protected T loaded;

  private final int timeout = DEFAULT_TIMEOUT;

  protected void resetState() {
    state = null;
  }

  protected void setState(final T state) {
    this.state = state;
  }

  protected void loaded(final T state) {
    this.loaded = state;
  }

  protected void delayedClear(final Runnable runner) {
    loaded = null;
    final T instanceState = state;
    SchedulerUtil.delay(() -> {
      if (this.state.equals(loaded)
          || !this.state.equals(instanceState)) {
        return;
      } else {
        runner.run();
      }
    }, timeout);
  }

  protected void ifMatchThen(final T hash, final Runnable runnable) {
    if (hash.equals(state)) {
      loaded(hash);
      runnable.run();
    }
  }

  protected void ifNotMatchThen(final T hash, final Runnable runnable) {
    if (!hash.equals(state)) {
      runnable.run();
    }
  }
}
