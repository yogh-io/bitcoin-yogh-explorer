package nl.yogh.wui.explorer.place;

import java.util.List;

import nl.aerius.wui.place.ApplicationPlace;

public class BlockTokenizer extends ApplicationPlace.Tokenizer<BlockPlace> {
  public BlockTokenizer() {
    super(() -> new BlockPlace());
  }

  @Override
  protected void updatePlace(final List<String> tokens, final BlockPlace place) {
    place.setHash(tokens.get(1));
  }

  @Override
  protected void setTokenList(final BlockPlace place, final List<String> tokens) {
    tokens.clear();
    tokens.add(place.getHash());
  }

  @Override
  public String getPrefix() {
    return ExplorerTokenizers.ALIAS_BLOCK;
  }
}
