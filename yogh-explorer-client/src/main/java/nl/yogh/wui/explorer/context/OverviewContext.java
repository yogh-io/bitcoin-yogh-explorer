package nl.yogh.wui.explorer.context;

import javax.inject.Singleton;

import com.axellience.vuegwt.core.annotations.component.Data;

import jsinterop.annotations.JsProperty;

import nl.yogh.wui.explorer.service.domain.BlockInformation;
import nl.yogh.wui.explorer.service.domain.MempoolInformation;
import nl.yogh.wui.explorer.service.domain.TransactionSummary;

@Singleton
public class OverviewContext {
  @Data @JsProperty public BlockInformation[] blocks = null;
  @Data @JsProperty public TransactionSummary[] transactions = null;
  @Data public MempoolInformation pool = null;

  @Data public boolean blocksLoading;
  @Data public boolean transactionsLoading;
  @Data public boolean mempoolLoading;

  @Data public Throwable failure = null;

  public void setFailure(final Throwable e) {
    clear();
    failure = e;
  }

  public void clear() {
    failure = null;
    blocks = null;
    transactions = null;
    pool = null;
  }

  public void setLoading() {
    blocksLoading = true;
    transactionsLoading = true;
    mempoolLoading = true;
  }

  public void setMempool(final MempoolInformation mempool) {
    mempoolLoading = false;
    this.pool = mempool;
  }

  public void setRecentBlocks(final BlockInformation[] blocks) {
    blocksLoading = false;
    this.blocks = blocks;
  }

  public void setRecentTransactions(final TransactionSummary[] transactions) {
    transactionsLoading = false;
    this.transactions = transactions;
  }
}
