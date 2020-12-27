package nl.yogh.wui.explorer.place;

import java.util.List;

import nl.aerius.wui.place.ApplicationPlace;

public class MempoolTokenizer extends ApplicationPlace.Tokenizer<MempoolPlace> {
  public MempoolTokenizer() {
    super(() -> new MempoolPlace());
  }

  @Override
  protected void updatePlace(final List<String> tokens, final MempoolPlace place) {}

  @Override
  protected void setTokenList(final MempoolPlace place, final List<String> tokens) {}

  @Override
  public String getPrefix() {
    return ExplorerTokenizers.ALIAS_MEMPOOL;
  }
}
