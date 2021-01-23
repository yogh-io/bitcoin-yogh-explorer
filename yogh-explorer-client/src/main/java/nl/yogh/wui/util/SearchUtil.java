package nl.yogh.wui.util;

import com.google.web.bindery.event.shared.binder.GenericEvent;

import nl.yogh.wui.explorer.command.LoadAddressCommand;
import nl.yogh.wui.explorer.command.LoadBlockCommand;
import nl.yogh.wui.explorer.command.LoadBlockHeightCommand;
import nl.yogh.wui.explorer.component.links.LoadTransactionCommand;

public final class SearchUtil {
  private static final String LAST_BLOCK_TOKEN = "last";

  // Bunch of zeroes every valid block starts with
  private static final String BLOCK_TOKEN_START = "0000000000";

  // Hash length (256 bit) if encoded into hex
  private static final int HASH_LENGTH = 64;

  // Allows for block heights up to 9999999, will last until the year 2190
  private static final int MAX_BLOCK_HEIGHT_NUMBER_LENGTH = 7;

  private SearchUtil() {}

  public static GenericEvent parseToken(final String token) {
    // Clean up the token first
    final String cleanToken = token.replace(" ", "");

    // Check for keywords
    if (LAST_BLOCK_TOKEN.equals(cleanToken)) {
//      return new BlockPlace(BlockDataType.LAST);
    }

    // Check if the token is exactly equal to the length of a hash (32 bytes),
    // meaning this is probably a transaction or block hash
    if (cleanToken.length() == HASH_LENGTH) {
      // If it starts with a bunch of zeroes, it's probably a block, otherwise
      // it may be a transaction
      if (cleanToken.startsWith(BLOCK_TOKEN_START)) {
        return new LoadBlockCommand(cleanToken);
      } else {
        return new LoadTransactionCommand(cleanToken);
      }
    }

    // Check if the token is a number, probably block height if it is
    if (cleanToken.length() <= MAX_BLOCK_HEIGHT_NUMBER_LENGTH) {
      try {
        final int possibleBlockHeight = Integer.parseInt(cleanToken);

        // Can't be less than 0 though..
        if (possibleBlockHeight >= 0) {
          return new LoadBlockHeightCommand(cleanToken);
        }
      } catch (final NumberFormatException e) {
        // Eat, this is probably not a number ;)
      }
    }

    // Check if the token can be base58 decoded, and if so, if the result looks
    // like an address (20 byte payload)
//    try {
//      final Base58CheckContents address = AddressParseUtil.parseAddress(cleanToken);
//      if (address.getPayload().length == 20) {
//        return new AddressPlace(cleanToken);
//      }
//    } catch (final Exception e) {
//      // Guess not.
//    }

    // Try an address
    return new LoadAddressCommand(cleanToken);

    // Nope, don't know what to do with this, return null
//    return null;
  }
}
