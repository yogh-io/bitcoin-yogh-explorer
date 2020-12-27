package nl.yogh.wui.explorer.component.hex.interpreters;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.googlecode.gwt.crypto.bouncycastle.util.encoders.Hex;

import nl.yogh.wui.explorer.component.hex.Part;
import nl.yogh.wui.explorer.component.hex.color.BlockColors;
import nl.yogh.wui.explorer.component.hex.color.Color;
import nl.yogh.wui.util.ArrayUtil;

public class BlockInterpreter implements InterpretationStrategy {
  @Inject BlockColors colors;

  @Override
  public Part[] interpret(final String hex) {
    final byte[] bytes = Hex.decode(hex);

    final Part[] parts = new Part[6];

    parts[0] = buildPart(snatch(bytes, 0, 4), colors.blockVersion());
    parts[1] = buildPart(snatch(bytes, 4, 36), colors.blockHash());
    parts[2] = buildPart(snatch(bytes, 36, 68), colors.blockMerkleRoot());
    parts[3] = buildPart(snatch(bytes, 68, 72), colors.blockTime());
    parts[4] = buildPart(snatch(bytes, 72, 76), colors.blockBits());
    parts[5] = buildPart(snatch(bytes, 76, 80), colors.blockNonce());

    return parts;
  }

  private byte[] snatch(final byte[] bytes, final int a, final int b) {
    return ArrayUtil.arrayCopy(bytes, a, b);
  }

  private Part buildPart(final byte[] bytes, final Color color) {
    final Color forecolor = color.copy();
    final Color backcolor = color.copy();
    
    forecolor.setA(0.8);
    backcolor.setA(0.2);
    
    return new Part(asList(bytes), backcolor.getValue(), forecolor.getValue());
  }

  private List<Byte> asList(final byte[] bytes) {
    final List<Byte> bytesLst = new ArrayList<>();
    for (final byte b : bytes) {
      bytesLst.add(b);
    }
    return bytesLst;
  }
}
