package nl.yogh.wui.explorer.ui.mine;

import javax.inject.Inject;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Computed;
import com.axellience.vuegwt.core.annotations.component.Data;
import com.axellience.vuegwt.core.annotations.component.Prop;
import com.axellience.vuegwt.core.client.component.IsVueComponent;
import com.axellience.vuegwt.core.client.component.hooks.HasCreated;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.gwt.crypto.bouncycastle.util.encoders.Hex;

import nl.aerius.wui.place.PlaceController;
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
import nl.yogh.wui.explorer.ui.BitcoinUtilityComponent;
import nl.yogh.wui.util.ArrayUtil;
import nl.yogh.wui.util.BlockUtil;
import nl.yogh.wui.util.NicerComputeUtil;
import nl.yogh.wui.util.NumberEncodeUtil;

@Component(components = {
    BlockLink.class,
    TransactionLink.class,
    AddressLink.class,
    ColorField.class,
    LabeledValue.class,
    LoadingHeading.class,
    HexViewer.class
})
public class MineView extends BitcoinUtilityComponent implements IsVueComponent, HasCreated {
  @Prop EventBus eventBus;

  @Inject @Data BlockContext blockContext;

  @Inject PlaceController placeController;

  @Data @Inject ColorPicker picker;

  @Inject @Data BlockInterpreter blockInterpreter;
  @Inject @Data TransactionInterpreter transactionInterpreter;

  @Data InterpretationStrategy blockHashInterpreter = null;
  @Data InterpretationStrategy transactionHashInterpreter = null;
  @Data InterpretationStrategy difficultyTargetInterpreter = null;

  @Data String version = "01000000";
  @Data String prevBlockHash = "0000000000000000000000000000000000000000000000000000000000000000";
  @Data String merkleRoot = "1231231200000000000000000000000012312312000000000000000000000000";
  @Data String timestamp = "12312312";
  @Data String bits = "93ef0b17";

  @Computed
  public String getNonce() {
    return new String(Hex.encode(NumberEncodeUtil.encodeUint32(nonceNum)));
  }

  @Data long nonceNum;

  @Computed
  public String getComputedBlockHash() {
    return NicerComputeUtil.computeDoubleSHA256(getRawHeaders());
  }

  @Computed
  public String getRawHeaders() {
    return version
        + prevBlockHash
        + merkleRoot
        + timestamp
        + bits
        + getNonce();
  }

  @Computed
  public String getTargetDifficulty() {
    final byte[] bitsBytes = Hex.decode(bits);
    ArrayUtil.reverse(bitsBytes);

    return new String(Hex.encode(BlockUtil.getFullBaseDiffTarget(bitsBytes, true)));
  }

  @Computed
  public String getRawCoinbase() {
    return "01000000";
  }

  @Override
  public void created() {
    blockHashInterpreter = new ColorInterpreter(picker.blockHash());
    transactionHashInterpreter = new ColorInterpreter(picker.transactionHash());
    difficultyTargetInterpreter = new ColorInterpreter(picker.blockBits());
  }
}
