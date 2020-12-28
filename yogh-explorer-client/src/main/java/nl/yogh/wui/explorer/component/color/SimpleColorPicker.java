package nl.yogh.wui.explorer.component.color;

public class SimpleColorPicker implements ColorPicker {
  @Override
  public Color blockHash() {
    return ColorBuilder.interpret("green");
  }

  @Override
  public Color blockHeight() {
    return ColorBuilder.interpret("green");
  }

  @Override
  public Color blockConfirmations() {
    return ColorBuilder.interpret("grey");
  }

  @Override
  public Color blockNumTransactions() {
    return ColorBuilder.interpret("cornflowerblue");
  }

  @Override
  public Color blockSize() {
    return ColorBuilder.interpret("red");
  }

  @Override
  public Color blockVersion() {
    return ColorBuilder.interpret("grey");
  }

  @Override
  public Color blockMerkleRoot() {
    return ColorBuilder.interpret("cornflowerblue");
  }

  @Override
  public Color blockTime() {
    return ColorBuilder.interpret("red");
  }

  @Override
  public Color blockBits() {
    return ColorBuilder.interpret("teal");
  }

  @Override
  public Color blockNonce() {
    return ColorBuilder.interpret("lightgreen");
  }

  @Override
  public Color transactionHash() {
    return ColorBuilder.interpret("cornflowerblue");
  }

  @Override
  public Color transactionWitness() {
    return ColorBuilder.interpret("darkorange");
  }

  @Override
  public Color transactionWeight() {
    return ColorBuilder.interpret("darkorange");
  }
  @Override
  public Color transactionVSize() {
    return ColorBuilder.interpret("orangered");
  }
  @Override
  public Color transactionBaseSize() {
    return ColorBuilder.interpret("green");
  }
  @Override
  public Color transactionTotalSize() {
    return ColorBuilder.interpret("lightseagreen");
  }

  @Override
  public Color transactionConfirmedState() {
    return ColorBuilder.interpret("grey");
  }

  @Override
  public Color transactionConfirmations() {
    return ColorBuilder.interpret("grey");
  }

  @Override
  public Color transactionTime() {
    return ColorBuilder.interpret("grey");
  }

  @Override
  public Color transactionInputLength() {
    return ColorBuilder.interpret("pink");
  }

  @Override
  public Color transactionInputIndex() {
    return ColorBuilder.interpret("lightblue");
  }

  @Override
  public Color transactionScriptSigLength() {
    return ColorBuilder.interpret("mediumvioletred");
  }

  @Override
  public Color transactionScriptSigOpCode() {
    return ColorBuilder.interpret("red");
  }

  @Override
  public Color transactionScriptSigPushData() {
    return ColorBuilder.interpret("cyan");
  }

  @Override
  public Color transactionInputSequence() {
    return ColorBuilder.interpret("lightgreen");
  }

  @Override
  public Color transactionOutputAmount() {
    return ColorBuilder.interpret("gold");
  }

  @Override
  public Color transactionOutputLength() {
    return ColorBuilder.interpret("pink");
  }

  @Override
  public Color transactionScriptPubKeyLength() {
    return ColorBuilder.interpret("mediumvioletred");
  }

  @Override
  public Color transactionScriptPubKeyOpCode() {
    return ColorBuilder.interpret("red");
  }

  @Override
  public Color transactionScriptPubKeyPushData() {
    return ColorBuilder.interpret("green");
  }

  @Override
  public Color transactionPubKeySigPushDataExtra() {
    return ColorBuilder.interpret("teal");
  }

  @Override
  public Color transactionVersion() {
    return ColorBuilder.interpret("grey");
  }

  @Override
  public Color transactionLockTime() {
    return ColorBuilder.interpret("grey");
  }

  @Override
  public Color transactionArbitraryData() {
    return ColorBuilder.interpret("black");
  }

  @Override
  public Color stackData() {
    return ColorBuilder.interpret("teal");
  }

  @Override
  public Color stackSingle() {
    return ColorBuilder.interpret("maroon");
  }

  @Override
  public Color address() {
    return ColorBuilder.interpret("skyblue");
  }

  @Override
  public Color addressHex() {
    return ColorBuilder.interpret("teal");
  }

  @Override
  public Color addressPayload() {
    return ColorBuilder.interpret("red");
  }

  @Override
  public Color addressVersion() {
    return ColorBuilder.interpret("grey");
  }

  @Override
  public Color addressChecksum() {
    return ColorBuilder.interpret("green");
  }

  @Override
  public Color addressValidity() {
    return ColorBuilder.interpret("red");
  }

  @Override
  public Color addressAdverisedChecksum() {
    return ColorBuilder.interpret("pink");
  }

  @Override
  public Color addressComputedChecksum() {
    return ColorBuilder.interpret("cyan");
  }

  @Override
  public Color addressOutpointSpent() {
    return ColorBuilder.interpret("red");
  }

  @Override
  public Color addressOutpointUnspent() {
    return ColorBuilder.interpret("green");
  }

  @Override
  public Color addressNumberOfTransactions() {
    return ColorBuilder.interpret("cyan");
  }

  @Override
  public Color witnessMarker() {
    return ColorBuilder.interpret("darkorange");
  }

  @Override
  public Color witnessFlag() {
    return ColorBuilder.interpret("darkorange");
  }

  @Override
  public Color witnessItemLength() {
    return ColorBuilder.interpret("pink");
  }

  @Override
  public Color witnessPushDataLength() {
    return ColorBuilder.interpret("mediumvioletred");
  }

  @Override
  public Color witnessPushData() {
    return ColorBuilder.interpret("darkorange");
  }
  
  @Override
  public Color error() {
    return ColorBuilder.interpret("darkred");
  }

  @Override
  public Color blockWeight() {
    return ColorBuilder.interpret("darkorange");
  }
}