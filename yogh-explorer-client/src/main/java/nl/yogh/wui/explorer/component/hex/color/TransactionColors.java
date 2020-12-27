package nl.yogh.wui.explorer.component.hex.color;

public interface TransactionColors {

  Color transactionHash();

  Color transactionWitness();

  Color transactionWeight();

  Color transactionVSize();

  Color transactionBaseSize();

  Color transactionTotalSize();

  Color transactionConfirmedState();

  Color transactionConfirmations();

  Color transactionTime();

  Color transactionInputIndex();

  Color transactionScriptSigOpCode();

  Color transactionScriptSigPushData();

  Color transactionPubKeySigPushDataExtra();

  Color transactionInputSequence();

  Color transactionOutputAmount();

  Color transactionPubKeySigOpCode();

  Color transactionPubKeySigPushData();

  Color transactionVersion();

  Color transactionLockTime();

  Color transactionPubKeySigLength();

  Color transactionScriptSigLength();

  Color transactionOutputLength();

  Color transactionInputLength();

  Color transactionArbitraryData();
}