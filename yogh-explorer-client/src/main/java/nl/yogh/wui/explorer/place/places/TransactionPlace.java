package nl.yogh.wui.explorer.place.places;

import nl.aerius.wui.place.ApplicationPlace;
import nl.yogh.wui.explorer.place.ExplorerPlaces;
import nl.yogh.wui.explorer.place.ExplorerTokenizers;
import nl.yogh.wui.explorer.place.ExplorerPlaces.MainPlace;

public class TransactionPlace extends MainPlace {
  private String hash;

  public TransactionPlace() {
    super(ExplorerTokenizers.TRANSACTION);
  }

  public TransactionPlace(final String hash) {
    super(ExplorerTokenizers.TRANSACTION);
    this.hash = hash;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <E extends ApplicationPlace> E copyTo(final E copy) {
    if (copy instanceof TransactionPlace) {
      return (E) copyToBlock((TransactionPlace) copy);
    }
    return super.copyTo(copy);
  }

  private TransactionPlace copyToBlock(final TransactionPlace copy) {
    copy.setHash(hash);
    return copy;
  }

  public String getHash() {
    return hash;
  }

  public void setHash(final String hash) {
    this.hash = hash;
  }
}
