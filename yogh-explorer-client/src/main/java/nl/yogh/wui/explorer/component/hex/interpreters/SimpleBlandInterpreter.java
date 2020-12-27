package nl.yogh.wui.explorer.component.hex.interpreters;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.gwt.crypto.bouncycastle.util.encoders.Hex;

import nl.yogh.wui.explorer.component.hex.Part;

public class SimpleBlandInterpreter implements InterpretationStrategy {
  @Override
  public Part[] interpret(final String hex) {
    final byte[] bytes = Hex.decode(hex);

    final List<Byte> bytesLst = new ArrayList<>();
    for (final byte b : bytes) {
      bytesLst.add(b);
    }

    return new Part[] { new Part(bytesLst, "#aaa", "#555") };
  }
}
