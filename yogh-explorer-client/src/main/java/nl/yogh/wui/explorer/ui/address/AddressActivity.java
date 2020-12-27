package nl.yogh.wui.explorer.ui.address;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.inject.assistedinject.Assisted;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import nl.aerius.wui.event.BasicEventComponent;
import nl.yogh.wui.explorer.command.LoadAddressCommand;
import nl.yogh.wui.explorer.event.SourceChangedEvent;
import nl.yogh.wui.explorer.place.AddressPlace;
import nl.yogh.wui.explorer.ui.MainView;

public class AddressActivity extends BasicEventComponent implements AddressPresenter {
  private static final AddressActivityEventBinder EVENT_BINDER = GWT.create(AddressActivityEventBinder.class);

  interface AddressActivityEventBinder extends EventBinder<AddressActivity> {}

  private final AddressPlace place;

  @Inject
  public AddressActivity(@Assisted final MainView view, @Assisted final AddressPlace place) {
    this.place = place;
    view.setMain(AddressViewFactory.get());
  }

  @Override
  public void onStart() {
    eventBus.fireEvent(new LoadAddressCommand(place.getHash()));
  }

  @EventHandler
  public void onSourceChangedEvent(final SourceChangedEvent e) {
    eventBus.fireEvent(new LoadAddressCommand(place.getHash()));
  }

  @Override
  public void setView(final AddressView view) {}

  @Override
  public void setEventBus(final EventBus eventBus) {
    super.setEventBus(eventBus, this, EVENT_BINDER);
  }
}
