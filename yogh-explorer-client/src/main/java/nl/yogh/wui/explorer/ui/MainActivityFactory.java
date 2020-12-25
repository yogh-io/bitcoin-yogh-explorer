package nl.yogh.wui.explorer.ui;

import nl.yogh.wui.explorer.place.BlockPlace;
import nl.yogh.wui.explorer.place.ExplorerPlaces.LandingPlace;
import nl.yogh.wui.explorer.place.TransactionPlace;
import nl.yogh.wui.explorer.ui.block.BlockActivity;
import nl.yogh.wui.explorer.ui.landing.LandingActivity;
import nl.yogh.wui.explorer.ui.transaction.TransactionActivity;

public interface MainActivityFactory {
  LandingActivity createLandingPresenter(MainView view, LandingPlace place);

  TransactionActivity createTransactionPresenter(MainView view, TransactionPlace place);

  BlockActivity createBlockPresenter(MainView view, BlockPlace place);
}
