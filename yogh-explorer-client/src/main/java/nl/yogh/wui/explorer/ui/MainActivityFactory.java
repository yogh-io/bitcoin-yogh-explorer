package nl.yogh.wui.explorer.ui;

import nl.yogh.wui.explorer.place.ExplorerPlaces.LandingPlace;
import nl.yogh.wui.explorer.place.ExplorerPlaces.MinePlace;
import nl.yogh.wui.explorer.place.places.AddressPlace;
import nl.yogh.wui.explorer.place.places.BlockHeightPlace;
import nl.yogh.wui.explorer.place.places.BlockPlace;
import nl.yogh.wui.explorer.place.places.MempoolPlace;
import nl.yogh.wui.explorer.place.places.TransactionPlace;
import nl.yogh.wui.explorer.ui.address.AddressActivity;
import nl.yogh.wui.explorer.ui.block.BlockActivity;
import nl.yogh.wui.explorer.ui.block.BlockHeightActivity;
import nl.yogh.wui.explorer.ui.landing.LandingActivity;
import nl.yogh.wui.explorer.ui.mempool.MempoolActivity;
import nl.yogh.wui.explorer.ui.mine.MineActivity;
import nl.yogh.wui.explorer.ui.transaction.TransactionActivity;

public interface MainActivityFactory {
  LandingActivity createLandingPresenter(MainView view, LandingPlace place);

  TransactionActivity createTransactionPresenter(MainView view, TransactionPlace place);

  BlockActivity createBlockPresenter(MainView view, BlockPlace place);

  BlockHeightActivity createBlockHeightPresenter(MainView view, BlockHeightPlace place);

  AddressActivity createAddressPresenter(MainView view, AddressPlace place);

  MempoolActivity createMempoolPresenter(MainView view, MempoolPlace place);

  MineActivity createMinePresenter(MainView view, MinePlace place);
}
