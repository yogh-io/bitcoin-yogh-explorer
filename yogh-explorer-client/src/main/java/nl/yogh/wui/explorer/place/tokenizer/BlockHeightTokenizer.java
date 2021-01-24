package nl.yogh.wui.explorer.place.tokenizer;

import java.util.List;

import nl.aerius.wui.place.ApplicationPlace;
import nl.yogh.wui.explorer.place.ExplorerTokenizers;
import nl.yogh.wui.explorer.place.places.BlockHeightPlace;

public class BlockHeightTokenizer extends ApplicationPlace.Tokenizer<BlockHeightPlace> {
  public BlockHeightTokenizer() {
    super(() -> new BlockHeightPlace());
  }

  @Override
  protected void updatePlace(final List<String> tokens, final BlockHeightPlace place) {
    place.setHeight(tokens.get(1));
  }

  @Override
  protected void setTokenList(final BlockHeightPlace place, final List<String> tokens) {
    tokens.clear();
    tokens.add(String.valueOf(place.getHeight()));
  }

  @Override
  public String getPrefix() {
    return ExplorerTokenizers.ALIAS_BLOCK_HEIGHT;
  }
}
