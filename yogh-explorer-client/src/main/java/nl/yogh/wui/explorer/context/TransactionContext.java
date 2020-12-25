package nl.yogh.wui.explorer.context;

import javax.inject.Singleton;

import com.axellience.vuegwt.core.annotations.component.Data;

import nl.yogh.wui.explorer.service.domain.TransactionInformation;

@Singleton
public class TransactionContext {
  @Data TransactionInformation transactionInformation = null;
  @Data String raw = null;

  @Data Throwable failure = null;

  public void setRawBlock(final String raw) {
    this.raw = raw;
  }

  public void setFailure(final Throwable e) {
    clear();
    failure = e;
  }

  public void clear() {
    transactionInformation = null;
    raw = null;
    failure = null;
  }

  public TransactionInformation getTransactionInformation() {
    return transactionInformation;
  }

  public void setTransactionInformation(final TransactionInformation transactionInformation) {
    this.transactionInformation = transactionInformation;
  }
}
