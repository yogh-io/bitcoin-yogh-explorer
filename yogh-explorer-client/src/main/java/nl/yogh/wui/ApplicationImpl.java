package nl.yogh.wui;

import com.google.inject.Inject;

import nl.aerius.wui.dev.GWTProd;
import nl.yogh.wui.explorer.ApplicationGinjector;
import nl.yogh.wui.resources.R;

public class ApplicationImpl extends Application {
  @Inject private ApplicationRoot appRoot;

  @Override
  public void create(final Runnable finish) {
    R.init();

    ApplicationGinjector.INSTANCE.inject(this);

    onFinishedLoading(finish);
  }

  /**
   * Initializes the application activity managers, user interface, and starts the
   * application by handling the current history token.
   * 
   * @param finish
   */
  private void onFinishedLoading(final Runnable finish) {
    try {
      appRoot.startUp(finish);
    } catch (final Exception e) {
      GWTProd.log("Failure while starting up.");

      logRecursive(e);
      appRoot.hideDisplay(e, finish);
      throw e;
    }
  }

  private void logRecursive(final Throwable e) {
    if (e == null) {
      return;
    }

    GWTProd.error("Cause: " + e);
    e.printStackTrace();
    logRecursive(e.getCause());

  }
}
