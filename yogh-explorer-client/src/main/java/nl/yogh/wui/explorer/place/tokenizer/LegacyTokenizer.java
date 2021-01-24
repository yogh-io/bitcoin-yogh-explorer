package nl.yogh.wui.explorer.place.tokenizer;

import nl.aerius.wui.dev.GWTProd;
import nl.aerius.wui.place.ApplicationPlace;
import nl.aerius.wui.place.PlaceTokenizer;
import nl.yogh.wui.explorer.place.places.AddressPlace;
import nl.yogh.wui.explorer.place.places.BlockHeightPlace;
import nl.yogh.wui.explorer.place.places.BlockPlace;
import nl.yogh.wui.explorer.place.places.TransactionPlace;

public class LegacyTokenizer implements PlaceTokenizer<ApplicationPlace> {
  @Override
  public ApplicationPlace getPlace(final String token) {
    GWTProd.log("Parsing legacy place token: " + token);

    final ApplicationPlace place;

    final String[] parts = token.split(":");
    final String type = parts[0];
    switch (type) {
    case "block":
      final String blockType = parts[1];
      switch (blockType) {
      case "hash":
        place = new BlockPlace(parts[2]);
        break;
      case "height":
        place = new BlockHeightPlace(parts[2]);
        break;
      case "last":
      default:
        place = new BlockHeightPlace("0");
        break;
      }
      break;
    case "tx":
      final String transactionType = parts[1];
      switch (transactionType) {
      default:
      case "id":
        place = new TransactionPlace(parts[2]);
        break;
      }
      break;
    case "addr":
      final String addressType = parts[1];
      switch (addressType) {
      default:
      case "id":
        place = new AddressPlace(parts[2]);
        break;
      }
      break;
    default:
      place = null;
      break;
    }

    return place;

  }

  @Override
  public String getToken(final ApplicationPlace place) {
    return null;
  }

  @Override
  public String getPrefix() {
    return "#";
  }
}
