package nl.yogh.wui.explorer.mapper;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import nl.aerius.wui.dev.GWTProd;
import nl.aerius.wui.history.PlaceHistoryMapper;
import nl.aerius.wui.place.PlaceTokenizer;
import nl.aerius.wui.place.TokenizedPlace;
import nl.yogh.wui.explorer.place.ExplorerTokenizers;

public class ExplorerPlaceHistoryMapper implements PlaceHistoryMapper {
  private final Map<String, PlaceTokenizer<? extends TokenizedPlace>> tokenizers = new LinkedHashMap<>();

  public ExplorerPlaceHistoryMapper() {
    init(ExplorerTokenizers.TOKENIZERS);
  }

  private void init(final PlaceTokenizer<?>... tokenizers) {
    Stream.of(tokenizers).forEach(this::init);
  }

  private void init(final PlaceTokenizer<?> tokenizer) {
    tokenizers.put(tokenizer.getPrefix(), tokenizer);
  }

  @Override
  public String getToken(final TokenizedPlace value) {
    return value.getToken();
  }

  @Override
  public TokenizedPlace getPlace(final String fullToken) {
    final String trimmedToken = trimToken(fullToken);

    GWTProd.log("Getting place for token: " + trimmedToken);

    return tokenizers.keySet().stream()
        .filter(v -> trimmedToken.startsWith(v))
        .findFirst()
        .map(v -> tokenizers.get(v).getPlace(fullToken.substring(v.length())))
        .orElse(null);
  }

  private String trimToken(final String fullToken) {
    return trimEnd(trimStart(fullToken));
  }

  private String trimEnd(final String token) {
    return token.lastIndexOf("/") == token.length() - 1 ? trimEnd(token.substring(0, token.length() - 1)) : token;
  }

  private String trimStart(final String token) {
    return token.indexOf("/") == 0 ? trimStart(token.substring(1)) : token;
  }
}
