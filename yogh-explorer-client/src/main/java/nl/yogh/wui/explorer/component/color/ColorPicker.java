package nl.yogh.wui.explorer.component.color;

public interface ColorPicker extends BlockColors, TransactionColors, AddressColors {
  Color stackData();

  Color stackSingle();

  Color witnessMarker();

  Color witnessFlag();

  Color error();
}
