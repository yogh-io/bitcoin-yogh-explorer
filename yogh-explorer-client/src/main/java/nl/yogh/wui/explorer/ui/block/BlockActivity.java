package nl.yogh.wui.explorer.ui.block;

import javax.inject.Inject;

import com.google.inject.assistedinject.Assisted;

import nl.aerius.wui.event.BasicEventComponent;
import nl.yogh.wui.explorer.command.LoadBlockCommand;
import nl.yogh.wui.explorer.place.BlockPlace;
import nl.yogh.wui.explorer.ui.MainView;

public class BlockActivity extends BasicEventComponent implements BlockPresenter {
  private final BlockPlace place;

  @Inject
  public BlockActivity(@Assisted final MainView view, @Assisted final BlockPlace place) {
    this.place = place;
    view.setMain(BlockViewFactory.get());
  }

  @Override
  public void onStart() {
    eventBus.fireEvent(new LoadBlockCommand(place.getHash()));
  }

  @Override
  public void setView(final BlockView view) {}
}
