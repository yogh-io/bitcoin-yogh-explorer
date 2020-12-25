package nl.yogh.wui.explorer.event;

import nl.aerius.wui.event.SimpleGenericEvent;
import nl.yogh.wui.explorer.service.domain.BlockInformation;

public class BlockLoadedEvent extends SimpleGenericEvent<BlockInformation> {
  public BlockLoadedEvent(final BlockInformation value) {
    super(value);
  }
}
