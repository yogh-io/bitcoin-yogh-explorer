package nl.yogh.wui.explorer.component.links;

import nl.aerius.wui.event.SimpleGenericEvent;

public class LoadTransactionCommand extends SimpleGenericEvent<String> {
  public LoadTransactionCommand(final String value) {
    super(value);
  }
}
