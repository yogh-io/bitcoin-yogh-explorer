package nl.yogh.wui;

import com.google.gwt.dom.client.Document;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

import nl.aerius.wui.activity.ActivityManager;
import nl.aerius.wui.dev.DevelopmentObserver;
import nl.aerius.wui.dev.GWTProd;
import nl.aerius.wui.history.HistoryManager;
import nl.aerius.wui.init.Initializer;
import nl.aerius.wui.util.WebUtil;
import nl.aerius.wui.vue.AcceptsOneComponent;
import nl.yogh.wui.explorer.component.error.ApplicationErrorView;
import nl.yogh.wui.explorer.component.error.ApplicationErrorViewFactory;
import nl.yogh.wui.explorer.daemon.DaemonBootstrapper;

/**
 * Root of the application logic.
 */
public class ApplicationRoot {
  @SuppressWarnings("unused") @Inject private DevelopmentObserver development;

  @Inject private HistoryManager historyManager;

  @Inject private EventBus eventBus;

  @Inject DaemonBootstrapper daemonBootstrapper;

  @Inject ActivityManager<AcceptsOneComponent> activityManager;

  @Inject Initializer initializer;

  @Inject VueRootViewFactory rootViewFactory;

  private VueRootView rootView;

  /**
   * Starts the application.
   */
  public void startUp(final Runnable finish) {
    final String root = Document.get().getElementsByTagName("base").getItem(0).getAttribute("href");
    WebUtil.setAbsoluteRoot(root);

    daemonBootstrapper.setEventBus(eventBus);

    rootView = rootViewFactory.create();
    activityManager.setPanel(rootView);

    initializer.init(() -> this.onFinishStartup(finish), e -> this.hideDisplay(e, finish));
  }

  private void onFinishStartup(final Runnable finish) {
    finish.run();
    rootView.vue().$mount("#base");

    historyManager.handleCurrentHistory();
  }

  /**
   * Hides the main application display, if attached.
   */
  public void hideDisplay(final Throwable e, final Runnable finish) {
    finish.run();
    GWTProd.error("Aborting startup due to fatal error: " + e.getMessage());
    final ApplicationErrorView err = ApplicationErrorViewFactory.get().create();
    err.setError(e);
    err.vue().$mount("#error");
  }
}
