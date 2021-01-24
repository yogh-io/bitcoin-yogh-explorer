package nl.yogh.wui.explorer.ui.block;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.inject.assistedinject.Assisted;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;

import nl.aerius.wui.event.BasicEventComponent;
import nl.yogh.wui.explorer.command.LoadBlockHeightCommand;
import nl.yogh.wui.explorer.place.places.BlockHeightPlace;
import nl.yogh.wui.explorer.ui.MainView;

public class BlockHeightActivity extends BasicEventComponent implements BlockPresenter {
  private static final BlockHeightActivityEventBinder EVENT_BINDER = GWT.create(BlockHeightActivityEventBinder.class);

  interface BlockHeightActivityEventBinder extends EventBinder<BlockHeightActivity> {}

  private final BlockHeightPlace place;

  @Inject
  public BlockHeightActivity(@Assisted final MainView view, @Assisted final BlockHeightPlace place) {
    this.place = place;
    view.setMain(BlockViewFactory.get());
  }

  @Override
  public void onStart() {
    eventBus.fireEvent(new LoadBlockHeightCommand(place.getHeight()));
  }

  @Override
  public void setView(final BlockView view) {}

  @Override
  public void setEventBus(final EventBus eventBus) {
    super.setEventBus(eventBus, this, EVENT_BINDER);
  }
}
