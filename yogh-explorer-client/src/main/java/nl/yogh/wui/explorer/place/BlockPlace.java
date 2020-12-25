package nl.yogh.wui.explorer.place;

import nl.aerius.wui.place.ApplicationPlace;
import nl.yogh.wui.explorer.place.ExplorerPlaces.MainPlace;

public class BlockPlace extends MainPlace {
  private String hash;

  public BlockPlace() {
    super(ExplorerTokenizers.BLOCK);
  }

  public BlockPlace(final String hash) {
    super(ExplorerTokenizers.BLOCK);
    this.hash = hash;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <E extends ApplicationPlace> E copyTo(final E copy) {
    if (copy instanceof BlockPlace) {
      return (E) copyToBlock((BlockPlace) copy);
    }
    return super.copyTo(copy);
  }

  private BlockPlace copyToBlock(final BlockPlace copy) {
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
