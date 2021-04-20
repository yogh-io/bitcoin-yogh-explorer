/*
 * Copyright the State of the Netherlands
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */
package nl.yogh.wui.explorer.ui;

import com.google.inject.Inject;

import nl.aerius.wui.dev.GWTProd;
import nl.aerius.wui.place.Place;
import nl.yogh.wui.explorer.place.ExplorerPlaces.LandingPlace;
import nl.yogh.wui.explorer.place.ExplorerPlaces.MinePlace;
import nl.yogh.wui.explorer.place.places.AddressPlace;
import nl.yogh.wui.explorer.place.places.BlockHeightPlace;
import nl.yogh.wui.explorer.place.places.BlockPlace;
import nl.yogh.wui.explorer.place.places.MempoolPlace;
import nl.yogh.wui.explorer.place.places.TransactionPlace;

public class MainActivityManager extends AbstractSubActivityManager<MainView, MainSubPresenter> {
  private final MainActivityFactory activityFactory;

  @Inject
  public MainActivityManager(final MainActivityFactory activityFactory) {
    this.activityFactory = activityFactory;
  }

  @Override
  public MainSubPresenter getActivity(final Place place, final MainView view) {
    if (place instanceof LandingPlace) {
      return activityFactory.createLandingPresenter(view, (LandingPlace) place);
    } else if (place instanceof TransactionPlace) {
      return activityFactory.createTransactionPresenter(view, (TransactionPlace) place);
    } else if (place instanceof BlockPlace) {
      return activityFactory.createBlockPresenter(view, (BlockPlace) place);
    } else if (place instanceof BlockHeightPlace) {
      return activityFactory.createBlockHeightPresenter(view, (BlockHeightPlace) place);
    } else if (place instanceof AddressPlace) {
      return activityFactory.createAddressPresenter(view, (AddressPlace) place);
    } else if (place instanceof MempoolPlace) {
      return activityFactory.createMempoolPresenter(view, (MempoolPlace) place);
    } else if (place instanceof MinePlace) {
      return activityFactory.createMinePresenter(view, (MinePlace) place);
    } else {
      GWTProd.warn("MainActivityManager", "Could not create sub-activity inside MainActivityManager: no activity for " + place);
//      return activityFactory.createEmptyActivity(view);
      return null;
    }
  }

  @Override
  protected Place getRedirect(final Place place) {
    final Place redirect = null;
    return redirect;
  }
}
