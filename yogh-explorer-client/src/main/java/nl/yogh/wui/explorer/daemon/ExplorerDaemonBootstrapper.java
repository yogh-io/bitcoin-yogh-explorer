package nl.yogh.wui.explorer.daemon;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

import nl.aerius.wui.event.BasicEventComponent;
import nl.yogh.wui.explorer.dev.ExplorerDevelopmentObserver;

public class ExplorerDaemonBootstrapper extends BasicEventComponent implements DaemonBootstrapper {
  @Inject ExplorerDevelopmentObserver developmentObserver;
  @Inject ExceptionDaemon exceptionDaemon;

  @Inject BlockDaemon blockDaemon;
  @Inject BlockchainDaemon blockchainDaemon;
  @Inject TransactionDaemon transactionDaemon;
  @Inject OverviewDaemon overviewDaemon;
  @Inject AddressDaemon addressDaemon;
  @Inject MempoolDaemon mempoolDaemon;

  @Override
  public void setEventBus(final EventBus eventBus) {
    super.setEventBus(eventBus, developmentObserver, exceptionDaemon, blockDaemon, blockchainDaemon, transactionDaemon, overviewDaemon,
        addressDaemon, mempoolDaemon);
  }
}
