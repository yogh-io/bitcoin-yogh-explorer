package nl.yogh.wui.explorer.factory;

import nl.yogh.wui.explorer.place.ExplorerPlaces.MainPlace;
import nl.yogh.wui.explorer.ui.MainActivity;

public interface ExplorerActivityFactory {
  MainActivity createMainPresenter(MainPlace place);
}
