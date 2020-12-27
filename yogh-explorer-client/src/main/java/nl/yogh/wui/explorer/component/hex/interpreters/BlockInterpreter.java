package nl.yogh.wui.explorer.component.hex.interpreters;

import javax.inject.Inject;

import com.googlecode.gwt.crypto.bouncycastle.util.encoders.Hex;

import nl.yogh.wui.explorer.component.hex.Part;
import nl.yogh.wui.explorer.component.hex.color.BlockColors;

public class BlockInterpreter extends BasicInterpreter implements InterpretationStrategy {
  @Inject BlockColors colors;

  @Override
  public Part[] interpret(final String hex) {
    final byte[] bytes = Hex.decode(hex);
    final Part[] parts = new Part[6];

    int pointer = 0;
    pointer = consume(bytes, pointer, 4, v -> parts[0] = v, colors.blockVersion());
    pointer = consume(bytes, pointer, 32, v -> parts[1] = v, colors.blockHash());
    pointer = consume(bytes, pointer, 32, v -> parts[2] = v, colors.blockMerkleRoot());
    pointer = consume(bytes, pointer, 4, v -> parts[3] = v, colors.blockTime());
    pointer = consume(bytes, pointer, 4, v -> parts[4] = v, colors.blockBits());
    pointer = consume(bytes, pointer, 4, v -> parts[5] = v, colors.blockNonce());

    return parts;
  }
}
