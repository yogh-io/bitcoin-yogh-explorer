package nl.yogh.wui.explorer.place;

import nl.aerius.wui.place.ApplicationPlace;
import nl.yogh.wui.explorer.place.ExplorerPlaces.MainPlace;

public class AddressPlace extends MainPlace {
  private String address;

  public AddressPlace() {
    super(ExplorerTokenizers.ADDRESS);
  }

  public AddressPlace(final String address) {
    super(ExplorerTokenizers.ADDRESS);
    this.address = address;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <E extends ApplicationPlace> E copyTo(final E copy) {
    if (copy instanceof AddressPlace) {
      return (E) copyToBlock((AddressPlace) copy);
    }
    return super.copyTo(copy);
  }

  private AddressPlace copyToBlock(final AddressPlace copy) {
    copy.setHash(address);
    return copy;
  }

  public String getHash() {
    return address;
  }

  public void setHash(final String hash) {
    this.address = hash;
  }
}
