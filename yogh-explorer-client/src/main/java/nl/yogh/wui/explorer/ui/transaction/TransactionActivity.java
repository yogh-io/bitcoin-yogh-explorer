package nl.yogh.wui.explorer.ui.transaction;

import javax.inject.Inject;

import com.google.inject.assistedinject.Assisted;

import nl.aerius.wui.event.BasicEventComponent;
import nl.yogh.wui.explorer.component.links.LoadTransactionCommand;
import nl.yogh.wui.explorer.place.TransactionPlace;
import nl.yogh.wui.explorer.ui.MainView;

public class TransactionActivity extends BasicEventComponent implements TransactionPresenter {
  private final TransactionPlace place;

  @Inject
  public TransactionActivity(@Assisted final MainView view, @Assisted final TransactionPlace place) {
    this.place = place;
    view.setMain(TransactionViewFactory.get());
  }

  @Override
  public void onStart() {
    eventBus.fireEvent(new LoadTransactionCommand(place.getHash()));
  }

  @Override
  public void setView(final TransactionView view) {}
}
