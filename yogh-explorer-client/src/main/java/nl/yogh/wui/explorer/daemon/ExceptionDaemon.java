package nl.yogh.wui.explorer.daemon;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;

import nl.aerius.wui.event.BasicEventComponent;
import nl.aerius.wui.event.HasEventBus;
import nl.aerius.wui.init.ExceptionalPirateCache;
import nl.aerius.wui.util.NotificationUtil;

public class ExceptionDaemon extends BasicEventComponent implements HasEventBus {
  private Throwable findCause(final Throwable e) {
    final Throwable cause;

    final Throwable booty = ExceptionalPirateCache.pop();
    if (booty == null) {
      if (e == null) {
        cause = null;
      } else if (e.getCause() == null) {
        cause = e;
      } else {
        // Recurse down
        cause = findCause(e.getCause());
      }
    } else {
      // Recurse on the hidden exception
      cause = findCause(booty);
    }

    return cause;
  }

  @Override
  public void setEventBus(final EventBus eventBus) {
    super.setEventBus(eventBus);

    GWT.setUncaughtExceptionHandler(e -> {
      final Throwable cause = findCause(e);

      NotificationUtil.broadcastError(eventBus, cause);
    });
  }
}
