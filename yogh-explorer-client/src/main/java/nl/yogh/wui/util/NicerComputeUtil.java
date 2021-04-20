package nl.yogh.wui.util;

import java.util.ArrayList;

import com.googlecode.gwt.crypto.bouncycastle.util.encoders.Hex;

public class NicerComputeUtil {
  public static String computeDoubleSHA256(final String str) {
    return new String(Hex.encode(ComputeUtil.computeDoubleSHA256(Hex.decode(str))));
  }

  public static String computeSHA256(final String str) {
    return new String(Hex.encode(ComputeUtil.computeSHA256(Hex.decode(str))));
  }

  @Deprecated
  public static String computeMerkleRoot(final String tx) {
    return new String(Hex.encode(ComputeUtil.computeDoubleSHA256(Hex.decode(tx))));
  }

  /**
   * TODO Test and support >1 tx
   */
  @Deprecated
  public static String computeMerkleRoot(final ArrayList<String> txs) {
    if (txs.size() > 1) {
      throw new IllegalStateException(">1 tx merkleroot not implemented.");
    }

    final String bs = txs.get(0);

    return computeDoubleSHA256(bs);
  }
}
