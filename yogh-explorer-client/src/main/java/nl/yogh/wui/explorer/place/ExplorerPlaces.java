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
package nl.yogh.wui.explorer.place;

import nl.aerius.wui.place.ApplicationPlace;
import nl.aerius.wui.place.PlaceTokenizer;

public final class ExplorerPlaces {
  private ExplorerPlaces() {}

  public static abstract class MainPlace extends ApplicationPlace {
    public MainPlace(final PlaceTokenizer<? extends MainPlace> tokenizer) {
      super(tokenizer);
    }
  }

  public static class LandingPlace extends MainPlace {
    public LandingPlace() {
      super(ExplorerTokenizers.LANDING);
    }
  }
}
