package nl.yogh.wui.explorer.context;

import javax.inject.Singleton;

import com.axellience.vuegwt.core.annotations.component.Data;

import nl.yogh.wui.explorer.service.domain.TransactionInformation;

@Singleton
public class TransactionContext {
  @Data public TransactionInformation transactionInformation = null;
  @Data public String raw = null;

  @Data public Throwable failure = null;
  @Data public boolean rawLoading;
  @Data public boolean transactionLoading;

  public void setRawTransaction(final String raw) {
    rawLoading = false;
    this.raw = raw;
  }

  public void setTransactionInformation(final TransactionInformation transactionInformation) {
    transactionLoading = false;
    this.transactionInformation = transactionInformation;
  }

  public void setFailure(final Throwable e) {
    clear();
    failure = e;
  }

  public void softClear() {
    failure = null;
  }

  public void clear() {
    softClear();
    transactionInformation = null;
    raw = null;
  }

  public void setLoading() {
    rawLoading = true;
    transactionLoading = true;
  }
}
