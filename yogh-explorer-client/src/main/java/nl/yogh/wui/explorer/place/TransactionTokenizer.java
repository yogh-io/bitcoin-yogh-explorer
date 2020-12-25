package nl.yogh.wui.explorer.place;

import java.util.List;

import nl.aerius.wui.place.ApplicationPlace;

public class TransactionTokenizer extends ApplicationPlace.Tokenizer<TransactionPlace> {
  public TransactionTokenizer() {
    super(() -> new TransactionPlace());
  }

  @Override
  protected void updatePlace(final List<String> tokens, final TransactionPlace place) {
    place.setHash(tokens.get(1));
  }

  @Override
  protected void setTokenList(final TransactionPlace place, final List<String> tokens) {
    tokens.clear();
    tokens.add(place.getHash());
  }

  @Override
  public String getPrefix() {
    return ExplorerTokenizers.ALIAS_TRANSACTION;
  }
}
