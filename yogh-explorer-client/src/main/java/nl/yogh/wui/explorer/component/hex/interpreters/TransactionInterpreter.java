package nl.yogh.wui.explorer.component.hex.interpreters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.googlecode.gwt.crypto.bouncycastle.util.encoders.Hex;

import nl.aerius.wui.dev.GWTProd;
import nl.yogh.wui.explorer.component.color.TransactionColors;
import nl.yogh.wui.explorer.component.hex.Part;
import nl.yogh.wui.util.VariableLengthInteger;

public class TransactionInterpreter extends BasicInterpreter implements InterpretationStrategy {
  @Inject TransactionColors colors;

  @Override
  public Part[] interpret(final String hex) {
    final byte[] bytes = Hex.decode(hex);

    final List<Part> parts = new ArrayList<>();

    try {
      int pointer = 0;
      pointer = consume(bytes, pointer, 4, parts::add, colors.transactionVersion());

      boolean segregatedWitnessEnabled = false;

      // Inputs
      final Semaphore<VariableLengthInteger> transactionInputLength = new Semaphore<>();
      pointer = consumeVarInt(bytes, pointer, parts::add, transactionInputLength::setObj, colors.transactionInputLength());
      if (transactionInputLength.getObj().getValue() == 0) {
        segregatedWitnessEnabled = true;
        pointer += 1;

        // pointer = parseWitnessFlag(transaction, pointer, bytes);
        parts.remove(parts.size() - 1);
        parts.add(buildPart(new byte[] {0x00, 0x01}, colors.transactionWitness()));

        // Parse the now-pushed-forward transaction input size
        pointer = consumeVarInt(bytes, pointer,
            parts::add,
            transactionInputLength::setObj,
            colors.transactionInputLength());
      }

      for (int i = 0; i < transactionInputLength.getObj().getValue(); i++) {
        pointer = consume(bytes, pointer, 32, parts::add, colors.transactionHash());
        pointer = consume(bytes, pointer, 4, parts::add, colors.transactionInputIndex());

        final Semaphore<VariableLengthInteger> scriptSigLength = new Semaphore<>();
        pointer = consumeVarInt(bytes, pointer, parts::add, scriptSigLength::setObj, colors.transactionScriptSigLength());

        // TODO Interpret the actual Script rather than the script sig data
        pointer = consume(bytes, pointer, (int) scriptSigLength.getObj().getValue(), parts::add, colors.transactionScriptSigPushData());

        pointer = consume(bytes, pointer, 4, parts::add, colors.transactionInputSequence());
      }

      // Outputs
      final Semaphore<VariableLengthInteger> transactionOutputLength = new Semaphore<>();
      pointer = consumeVarInt(bytes, pointer, parts::add, transactionOutputLength::setObj, colors.transactionOutputLength());

      for (int i = 0; i < transactionOutputLength.getObj().getValue(); i++) {
        pointer = consume(bytes, pointer, 8, parts::add, colors.transactionOutputAmount());

        final Semaphore<VariableLengthInteger> scriptPubKeyLength = new Semaphore<>();
        pointer = consumeVarInt(bytes, pointer, parts::add, scriptPubKeyLength::setObj, colors.transactionScriptPubKeyLength());

        // TODO Interpret the actual Script rather than the script pubkey data
        pointer = consume(bytes, pointer, (int) scriptPubKeyLength.getObj().getValue(), parts::add, colors.transactionScriptPubKeyPushData());
      }

      if (segregatedWitnessEnabled) {
        for (int i = 0; i < transactionInputLength.getObj().getValue(); i++) {
          final Semaphore<VariableLengthInteger> witnessItemLength = new Semaphore<>();
          pointer = consumeVarInt(bytes, pointer, parts::add, witnessItemLength::setObj, colors.witnessItemLength());

          for (int j = 0; j < witnessItemLength.getObj().getValue(); j++) {
            final Semaphore<VariableLengthInteger> witnessPushDataLength = new Semaphore<>();
            pointer = consumeVarInt(bytes, pointer, parts::add, witnessPushDataLength::setObj, colors.witnessPushDataLength());

            pointer = consume(bytes, pointer, (int) witnessPushDataLength.getObj().getValue(), parts::add, colors.witnessPushData());
          }
        }
      }

      pointer = consume(bytes, pointer, 4, parts::add, colors.transactionLockTime());

      if (bytes.length != pointer) {
        GWTProd.warn("Consumed a differend amount of bytes than available (" + pointer + "/" + bytes.length + ")");
      }
    } catch (final Exception e) {
      e.printStackTrace();
      GWTProd.warn("Could not parse transaction: " + e.getMessage());
    }

    return parts.stream()
        .filter(v -> v != null)
        .collect(Collectors.toList())
        .toArray(new Part[parts.size()]);
  }

}
