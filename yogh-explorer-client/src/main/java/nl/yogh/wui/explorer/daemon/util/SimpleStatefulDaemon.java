package nl.yogh.wui.explorer.daemon.util;

import nl.aerius.wui.event.BasicEventComponent;

public abstract class SimpleStatefulDaemon<T> extends BasicEventComponent {
  protected T state;

  protected void resetState() {
    state = null;
  }

  protected void setState(final T state) {
    this.state = state;
  }

  protected void ifMatchThen(final T hash, final Runnable runnable) {
    if (hash.equals(state)) {
      runnable.run();
    }
  }

  protected void ifNotMatchThen(final T hash, final Runnable runnable) {
    if (!hash.equals(state)) {
      runnable.run();
    }
  }
}
