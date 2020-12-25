package nl.yogh.wui.explorer.context;

import javax.inject.Singleton;

import com.axellience.vuegwt.core.annotations.component.Data;

import nl.yogh.wui.explorer.service.domain.TransactionInformation;

@Singleton
public class TransactionContext {
  @Data public TransactionInformation transactionInformation = null;
  @Data public String raw = null;

  @Data public Throwable failure = null;
  @Data public boolean transactionLoading;

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
  
  public void setLoading() {
    transactionLoading = true;
  }

  public void setTransactionInformation(final TransactionInformation transactionInformation) {
    this.transactionInformation = transactionInformation;
  }
}
