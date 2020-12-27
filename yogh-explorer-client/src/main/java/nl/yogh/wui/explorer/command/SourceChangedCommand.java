package nl.yogh.wui.explorer.command;

import nl.aerius.wui.command.SimpleCommand;
import nl.yogh.wui.explorer.event.SourceChangedEvent;

public class SourceChangedCommand extends SimpleCommand<SourceChangedEvent> {
  @Override
  protected SourceChangedEvent createEvent() {
    return new SourceChangedEvent();
  }
}
