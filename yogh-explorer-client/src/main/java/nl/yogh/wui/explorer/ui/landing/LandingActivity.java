package nl.yogh.wui.explorer.ui.landing;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.inject.assistedinject.Assisted;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;

import nl.aerius.wui.event.BasicEventComponent;
import nl.yogh.wui.explorer.command.ActivateOverviewCommand;
import nl.yogh.wui.explorer.command.DeactivateOverviewCommand;
import nl.yogh.wui.explorer.ui.MainView;

public class LandingActivity extends BasicEventComponent implements LandingPresenter {
  private static final LandingActivityEventBinder EVENT_BINDER = GWT.create(LandingActivityEventBinder.class);

  interface LandingActivityEventBinder extends EventBinder<LandingActivity> {}

  @Inject
  public LandingActivity(@Assisted final MainView view) {
    view.setMain(LandingViewFactory.get());
  }

  @Override
  public void onStart() {
    eventBus.fireEvent(new ActivateOverviewCommand());
  }

  @Override
  public void onStop() {
    eventBus.fireEvent(new DeactivateOverviewCommand());
  }

  @Override
  public void setView(final LandingView view) {}

  @Override
  public void setEventBus(final EventBus eventBus) {
    super.setEventBus(eventBus, this, EVENT_BINDER);
  }
}
