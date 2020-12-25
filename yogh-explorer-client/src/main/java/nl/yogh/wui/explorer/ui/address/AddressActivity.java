package nl.yogh.wui.explorer.ui.address;

import javax.inject.Inject;

import com.google.inject.assistedinject.Assisted;

import nl.aerius.wui.event.BasicEventComponent;
import nl.yogh.wui.explorer.command.LoadAddressCommand;
import nl.yogh.wui.explorer.place.AddressPlace;
import nl.yogh.wui.explorer.ui.MainView;

public class AddressActivity extends BasicEventComponent implements AddressPresenter {
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

  @Override
  public void setView(final AddressView view) {}
}
