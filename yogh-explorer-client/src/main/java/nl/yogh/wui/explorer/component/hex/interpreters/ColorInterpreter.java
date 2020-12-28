package nl.yogh.wui.explorer.component.hex.interpreters;

import com.googlecode.gwt.crypto.bouncycastle.util.encoders.Hex;

import nl.yogh.wui.explorer.component.color.Color;
import nl.yogh.wui.explorer.component.hex.Part;

public class ColorInterpreter extends BasicInterpreter implements InterpretationStrategy {
  private final Color color;

  public ColorInterpreter(final Color color) {
    this.color = color;
  }

  @Override
  public Part[] interpret(final String hex) {
    final byte[] bytes = Hex.decode(hex);

    return new Part[] { buildPart(bytes, color) };
  }
}
