package nl.yogh.wui.explorer.place;

import nl.aerius.wui.place.ApplicationPlace;
import nl.yogh.wui.explorer.place.ExplorerPlaces.MainPlace;

public class BlockHeightPlace extends MainPlace {
  private String height;

  public BlockHeightPlace() {
    super(ExplorerTokenizers.BLOCK_HEIGHT);
  }

  public BlockHeightPlace(final String height) {
    super(ExplorerTokenizers.BLOCK_HEIGHT);
    this.height = height;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <E extends ApplicationPlace> E copyTo(final E copy) {
    if (copy instanceof BlockHeightPlace) {
      return (E) copyToBlock((BlockHeightPlace) copy);
    }
    return super.copyTo(copy);
  }

  private BlockHeightPlace copyToBlock(final BlockHeightPlace copy) {
    copy.setHeight(height);
    return copy;
  }

  public String getHeight() {
    return height;
  }

  public void setHeight(final String height) {
    this.height = height;
  }
}
