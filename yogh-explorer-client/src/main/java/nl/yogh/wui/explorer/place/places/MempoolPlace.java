package nl.yogh.wui.explorer.place.places;

import nl.aerius.wui.place.ApplicationPlace;
import nl.yogh.wui.explorer.place.ExplorerPlaces;
import nl.yogh.wui.explorer.place.ExplorerTokenizers;
import nl.yogh.wui.explorer.place.ExplorerPlaces.MainPlace;

public class MempoolPlace extends MainPlace {
  public MempoolPlace() {
    super(ExplorerTokenizers.MEMPOOL);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <E extends ApplicationPlace> E copyTo(final E copy) {
    if (copy instanceof MempoolPlace) {
      return (E) copyToMempool((MempoolPlace) copy);
    }
    return super.copyTo(copy);
  }

  private MempoolPlace copyToMempool(final MempoolPlace copy) {
    return copy;
  }
}
