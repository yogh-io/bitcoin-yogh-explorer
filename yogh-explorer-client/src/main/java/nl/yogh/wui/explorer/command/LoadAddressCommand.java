package nl.yogh.wui.explorer.command;

import nl.aerius.wui.event.SimpleGenericEvent;

public class LoadAddressCommand extends SimpleGenericEvent<String> {
  public LoadAddressCommand(final String value) {
    super(value);
  }
}
