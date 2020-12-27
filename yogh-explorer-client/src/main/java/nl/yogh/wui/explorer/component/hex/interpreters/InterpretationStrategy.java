package nl.yogh.wui.explorer.component.hex.interpreters;

import nl.yogh.wui.explorer.component.hex.Part;

public interface InterpretationStrategy {
  Part[] interpret(String hex);
}
