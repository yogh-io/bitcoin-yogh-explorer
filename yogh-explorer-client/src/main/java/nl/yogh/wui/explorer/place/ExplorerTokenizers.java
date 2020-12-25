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

import nl.aerius.wui.place.ApplicationPlace.Tokenizer;
import nl.aerius.wui.place.PlaceTokenizer;
import nl.yogh.wui.explorer.place.ExplorerPlaces.LandingPlace;

public class ExplorerTokenizers {
  private static final String ALIAS_LANDING = "landing";
  public static final String ALIAS_TRANSACTION = "tx";
  public static final String ALIAS_BLOCK = "block";

  public static final PlaceTokenizer<LandingPlace> LANDING = Tokenizer.create(() -> new LandingPlace(), ALIAS_LANDING);

  public static final PlaceTokenizer<TransactionPlace> TRANSACTION = new TransactionTokenizer();
  public static final PlaceTokenizer<BlockPlace> BLOCK = new BlockTokenizer();

  public static final PlaceTokenizer<?>[] TOKENIZERS = new PlaceTokenizer[] {
      LANDING, TRANSACTION, BLOCK
  };

}
