package nl.yogh.wui.explorer.command;

import nl.aerius.wui.event.SimpleGenericEvent;

public class LoadBlockHeightCommand extends SimpleGenericEvent<String> {
  public LoadBlockHeightCommand(final String value) {
    super(value);
  }
}
