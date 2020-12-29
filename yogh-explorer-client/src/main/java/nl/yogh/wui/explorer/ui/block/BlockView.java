package nl.yogh.wui.explorer.ui.block;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Computed;
import com.axellience.vuegwt.core.annotations.component.Data;
import com.axellience.vuegwt.core.annotations.component.Prop;
import com.axellience.vuegwt.core.client.component.IsVueComponent;
import com.axellience.vuegwt.core.client.component.hooks.HasCreated;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.gwt.crypto.bouncycastle.util.encoders.Hex;

import jsinterop.annotations.JsMethod;

import nl.yogh.wui.explorer.component.color.ColorPicker;
import nl.yogh.wui.explorer.component.fields.ColorField;
import nl.yogh.wui.explorer.component.fields.LabeledValue;
import nl.yogh.wui.explorer.component.hex.HexViewer;
import nl.yogh.wui.explorer.component.hex.interpreters.BlockInterpreter;
import nl.yogh.wui.explorer.component.hex.interpreters.ColorInterpreter;
import nl.yogh.wui.explorer.component.hex.interpreters.InterpretationStrategy;
import nl.yogh.wui.explorer.component.hex.interpreters.TransactionInterpreter;
import nl.yogh.wui.explorer.component.links.AddressLink;
import nl.yogh.wui.explorer.component.links.BlockLink;
import nl.yogh.wui.explorer.component.links.TransactionLink;
import nl.yogh.wui.explorer.component.misc.LoadingHeading;
import nl.yogh.wui.explorer.context.BlockContext;
import nl.yogh.wui.explorer.service.domain.BlockInformation;
import nl.yogh.wui.explorer.ui.BitcoinUtilityComponent;
import nl.yogh.wui.util.BlockUtil;
import nl.yogh.wui.util.NumberEncodeUtil;

@Component(components = {
    BlockLink.class,
    TransactionLink.class,
    AddressLink.class,
    HexViewer.class,
    LabeledValue.class,
    ColorField.class,
    LoadingHeading.class
})
public class BlockView extends BitcoinUtilityComponent implements IsVueComponent, HasCreated {
  @Prop EventBus eventBus;

  @Data @Inject BlockContext context;

  @Data int limit = 10;

  @Inject @Data BlockInterpreter blockInterpreter;
  @Inject @Data TransactionInterpreter transactionInterpreter;

  @Data @Inject ColorPicker picker;

  @Data InterpretationStrategy blockHashInterpreter = null;
  @Data InterpretationStrategy transactionHashInterpreter = null;
  @Data InterpretationStrategy difficultyTargetInterpreter = null;
  
  @JsMethod
  public String formatDifficultyTarget(final String bits) {
    final byte[] bitsBytes = NumberEncodeUtil.encodeUint32(Integer.parseInt(bits));
    final byte[] difficultyTarget = BlockUtil.getPoolDiffTarget(bitsBytes);
    return new String(Hex.encode(difficultyTarget));
  }

  @Computed
  public BlockInformation getBlock() {
    return context.blockInformation;
  }

  @Computed
  public String getRaw() {
    return context.raw;
  }

  @Computed
  public String[] getTxids() {
    return context.txids;
  }

  @JsMethod
  public List<String> limit(final String[] lst) {
    return Stream.of(lst)
        .limit(limit)
        .collect(Collectors.toList());
  }

  @JsMethod
  public void increaseLimit() {
    limit += 5;
  }

  @Override
  public void created() {
    blockHashInterpreter = new ColorInterpreter(picker.blockHash());
    transactionHashInterpreter = new ColorInterpreter(picker.transactionHash());
    difficultyTargetInterpreter = new ColorInterpreter(picker.blockBits());
  }
}
