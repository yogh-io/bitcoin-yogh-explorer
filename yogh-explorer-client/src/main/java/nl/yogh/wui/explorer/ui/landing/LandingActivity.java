package nl.yogh.wui.explorer.ui.landing;

import javax.inject.Inject;

import com.google.inject.assistedinject.Assisted;

import nl.aerius.wui.event.BasicEventComponent;
import nl.yogh.wui.explorer.command.UpdateRecentBlocksCommand;
import nl.yogh.wui.explorer.ui.MainView;

public class LandingActivity extends BasicEventComponent implements LandingPresenter {
  @Inject
  public LandingActivity(@Assisted final MainView view) {
    view.setMain(LandingViewFactory.get());
  }

  @Override
  public void onStart() {
    eventBus.fireEvent(new UpdateRecentBlocksCommand());
  }

  @Override
  public void setView(final LandingView view) {}
}
