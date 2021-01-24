package nl.yogh.wui.explorer.ui.block;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.inject.assistedinject.Assisted;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;

import nl.aerius.wui.event.BasicEventComponent;
import nl.yogh.wui.explorer.command.LoadBlockCommand;
import nl.yogh.wui.explorer.place.places.BlockPlace;
import nl.yogh.wui.explorer.ui.MainView;

public class BlockActivity extends BasicEventComponent implements BlockPresenter {
  private static final BlockActivityEventBinder EVENT_BINDER = GWT.create(BlockActivityEventBinder.class);

  interface BlockActivityEventBinder extends EventBinder<BlockActivity> {}

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

  @Override
  public void setEventBus(final EventBus eventBus) {
    super.setEventBus(eventBus, this, EVENT_BINDER);
  }
}
