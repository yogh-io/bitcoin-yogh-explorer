package nl.yogh.wui.explorer.component.hex.color;

public interface AddressColors {
  Color address();

  Color addressHex();

  Color addressPayload();

  Color addressVersion();

  Color addressChecksum();

  Color addressValidity();

  Color addressAdverisedChecksum();

  Color addressComputedChecksum();

  Color addressOutpointSpent();

  Color addressOutpointUnspent();

  Color addressNumberOfTransactions();
}
