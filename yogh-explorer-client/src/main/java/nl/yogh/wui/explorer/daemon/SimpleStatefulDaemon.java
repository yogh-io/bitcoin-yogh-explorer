package nl.yogh.wui.explorer.daemon;

import nl.aerius.wui.event.BasicEventComponent;
import nl.aerius.wui.util.SchedulerUtil;

public abstract class SimpleStatefulDaemon<T> extends BasicEventComponent {
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

  protected void delayedLoad(final Runnable runner) {
    loaded = null;
    SchedulerUtil.delay(() -> {
      if (this.state.equals(loaded)) {
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
