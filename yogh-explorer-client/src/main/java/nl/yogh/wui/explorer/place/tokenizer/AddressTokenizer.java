package nl.yogh.wui.explorer.place.tokenizer;

import java.util.List;

import nl.aerius.wui.place.ApplicationPlace;
import nl.yogh.wui.explorer.place.ExplorerTokenizers;
import nl.yogh.wui.explorer.place.places.AddressPlace;

public class AddressTokenizer extends ApplicationPlace.Tokenizer<AddressPlace> {
  public AddressTokenizer() {
    super(() -> new AddressPlace());
  }

  @Override
  protected void updatePlace(final List<String> tokens, final AddressPlace place) {
    place.setHash(tokens.get(1));
  }

  @Override
  protected void setTokenList(final AddressPlace place, final List<String> tokens) {
    tokens.clear();
    tokens.add(place.getHash());
  }

  @Override
  public String getPrefix() {
    return ExplorerTokenizers.ALIAS_ADDRESS;
  }
}
