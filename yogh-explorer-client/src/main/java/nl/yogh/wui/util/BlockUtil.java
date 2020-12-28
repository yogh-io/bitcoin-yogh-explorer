package nl.yogh.wui.util;

import java.math.BigInteger;

public class BlockUtil {
  private static final long BITS_BASE = 256;
  private static final int NUM_BYTES = (int) (BITS_BASE / 8);
  private static final int BITS_MANTISSA_LENGTH = 3;

  public static byte[] getDifficultyTarget(final byte[] bits) {
    assert bits.length == BITS_MANTISSA_LENGTH + 1 : "Bits length must be 4.";

    final BigInteger mantissa = new BigInteger(ArrayUtil.arrayCopy(bits, 1, 4));

    final BigInteger base = BigInteger.valueOf(BITS_BASE);

    final int exponent = (bits[0] & 0xFF) - BITS_MANTISSA_LENGTH;

    final BigInteger target = mantissa.multiply(base.pow(exponent));

    final byte[] targetBytes = target.toByteArray();

    return targetBytes;
  }

  public static byte[] getBitcoinDiffTarget(final byte[] bits) {
    return getFullBaseDiffTarget(bits, false);
  }

  public static byte[] getPoolDiffTarget(final byte[] bits) {
    return getFullBaseDiffTarget(bits, true);
  }

  public static byte[] getFullBaseDiffTarget(final byte[] bits, final boolean pool) {
    final byte[] target = getDifficultyTarget(bits);

    final byte[] fullBaseTarget = new byte[NUM_BYTES];

    int counter = NUM_BYTES - target.length;
    for (final byte bite : target) {
      if (pool && counter >= NUM_BYTES - target.length + BlockUtil.BITS_MANTISSA_LENGTH) {
        fullBaseTarget[counter] = (byte) (0xFF & 0xFF);
      } else {
        fullBaseTarget[counter] = (byte) (bite & 0xFF);
      }

      counter++;
    }

    return fullBaseTarget;
  }
}
