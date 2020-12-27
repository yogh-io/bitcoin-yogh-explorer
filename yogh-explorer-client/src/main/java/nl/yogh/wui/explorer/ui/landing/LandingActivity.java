package nl.yogh.wui.explorer.ui.landing;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.inject.assistedinject.Assisted;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import nl.aerius.wui.event.BasicEventComponent;
import nl.yogh.wui.explorer.command.UpdateRecentBlocksCommand;
import nl.yogh.wui.explorer.event.SourceChangedEvent;
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
    eventBus.fireEvent(new UpdateRecentBlocksCommand());
  }

  @EventHandler
  public void onSourceChangedEvent(final SourceChangedEvent e) {
    eventBus.fireEvent(new UpdateRecentBlocksCommand());
  }

  @Override
  public void setView(final LandingView view) {}

  @Override
  public void setEventBus(final EventBus eventBus) {
    super.setEventBus(eventBus, this, EVENT_BINDER);
  }
}
