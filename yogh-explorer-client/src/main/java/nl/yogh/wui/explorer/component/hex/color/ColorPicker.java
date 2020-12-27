package nl.yogh.wui.explorer.component.hex.color;

public interface ColorPicker extends BlockColors, TransactionColors, AddressColors {
  Color stackData();

  Color stackSingle();

  Color witnessMarker();

  Color witnessFlag();

  Color witnessItemLength();

  Color witnessPushData();

  Color witnessPushDataLength();

  Color error();
}
