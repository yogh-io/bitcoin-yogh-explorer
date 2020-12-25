package nl.yogh.wui.explorer.command;

import nl.aerius.wui.event.SimpleGenericEvent;

public class LoadBlockCommand extends SimpleGenericEvent<String> {
  public LoadBlockCommand(final String value) {
    super(value);
  }
}
