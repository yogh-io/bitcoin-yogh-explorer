package nl.yogh.wui.explorer.component.hex.interpreters;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import nl.aerius.wui.dev.GWTProd;
import nl.yogh.wui.explorer.component.color.Color;
import nl.yogh.wui.explorer.component.hex.Part;
import nl.yogh.wui.util.ArrayUtil;
import nl.yogh.wui.util.VariableLengthInteger;

public abstract class BasicInterpreter {
  protected int consume(final byte[] bytes, final int start, final int length, final Consumer<Part> consumer, final Color color) {
    final Color forecolor = color.copy();
    forecolor.setA(0.8);

    final Color backcolor = color.copy();
    backcolor.setA(0.2);

    final Part p = new Part(asList(snatch(bytes, start, start + length)), backcolor.getValue(), forecolor.getValue());
    if (!p.bytes.isEmpty()) {
      consumer.accept(p);
    } else {
      GWTProd.warn("No bytes for part: " + bytes + " > " + start + " > " + length);
    }
    
    return start + length;
  }

  protected int consumeVarInt(final byte[] bytes, final int pointer, final Consumer<Part> consumer, final Consumer<VariableLengthInteger> varInt,
      final Color color) {
    final VariableLengthInteger variableInteger = new VariableLengthInteger(bytes, pointer);
    varInt.accept(variableInteger);

    consumer.accept(buildPart(variableInteger.getBytes(), color));

    return pointer + variableInteger.getByteSize();
  }

  protected byte[] snatch(final byte[] bytes, final int a, final int b) {
    if (b > bytes.length) {
      return new byte[0];
    }

    return ArrayUtil.arrayCopy(bytes, a, b);
  }

  protected Part buildPart(final byte[] bytes, final Color color) {
    final Color forecolor = color.copy();
    forecolor.setA(0.8);

    final Color backcolor = color.copy();
    backcolor.setA(0.2);

    return new Part(asList(bytes), backcolor.getValue(), forecolor.getValue());
  }

  protected List<Byte> asList(final byte[] bytes) {
    final List<Byte> bytesLst = new ArrayList<>();
    for (final byte b : bytes) {
      bytesLst.add(b);
    }
    return bytesLst;
  }
}
