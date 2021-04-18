package nl.yogh.indexer.jobs;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.yogh.indexer.util.VariableLengthInteger;

public class TransactionUtil {
  private static final Logger LOG = LoggerFactory.getLogger(TransactionUtil.class);

  public static int fetchTransactionLength(final byte[] block, int pointer) {
    final int pointerStart = pointer;

    // Add version
    pointer += 4;

    final VariableLengthInteger preliminaryInputCount = new VariableLengthInteger(block, pointer);

    // If segwit marker, advance by 2 (marker and flag)
    final boolean segwit = preliminaryInputCount.getValue() == 0;
    if (segwit) {
      pointer += 2;
    }

    // Advance through the inputs
    final VariableLengthInteger inputCount = new VariableLengthInteger(block, pointer);
    pointer += inputCount.getByteSize();

    for (int j = 0; j < inputCount.getValue(); j++) {
      pointer += 32 + 4;

      final VariableLengthInteger scriptLength = new VariableLengthInteger(block, pointer);
      pointer += scriptLength.getByteSize();
      pointer += scriptLength.getValue();

      // Add sequence
      pointer += 4;
    }

    final VariableLengthInteger outputCount = new VariableLengthInteger(block, pointer);
    pointer += outputCount.getByteSize();

    for (int k = 0; k < outputCount.getValue(); k++) {
      // Add amount
      pointer += 8;

      final VariableLengthInteger scriptLength = new VariableLengthInteger(block, pointer);
      pointer += scriptLength.getByteSize();
      pointer += scriptLength.getValue();
    }

    if (segwit) {
      for (int m = 0; m < inputCount.getValue(); m++) {
        final VariableLengthInteger witnessCount = new VariableLengthInteger(block, pointer);
        pointer += witnessCount.getByteSize();

        for (int l = 0; l < witnessCount.getValue(); l++) {
          final VariableLengthInteger witnessLength = new VariableLengthInteger(block, pointer);
          pointer += witnessLength.getByteSize();
          pointer += witnessLength.getValue();
        }
      }
    }

    // Advance nlocktime bytes
    pointer += 4;

    return pointer - pointerStart;
  }

  private static String hex(final byte b) {
    return hex(new byte[] { b });
  }

  private static String hex(final byte b[]) {
    return new String(Hex.encodeHex(b));
  }
}
