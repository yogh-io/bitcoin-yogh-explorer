package nl.yogh.wui.explorer.ui.transaction;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.inject.assistedinject.Assisted;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import nl.aerius.wui.event.BasicEventComponent;
import nl.yogh.wui.explorer.component.links.LoadTransactionCommand;
import nl.yogh.wui.explorer.event.SourceChangedEvent;
import nl.yogh.wui.explorer.place.places.TransactionPlace;
import nl.yogh.wui.explorer.ui.MainView;

public class TransactionActivity extends BasicEventComponent implements TransactionPresenter {
  private static final TransactionActivityEventBinder EVENT_BINDER = GWT.create(TransactionActivityEventBinder.class);

  interface TransactionActivityEventBinder extends EventBinder<TransactionActivity> {}

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

  @EventHandler
  public void onSourceChangedEvent(final SourceChangedEvent e) {
    eventBus.fireEvent(new LoadTransactionCommand(place.getHash()));
  }

  @Override
  public void setView(final TransactionView view) {}

  @Override
  public void setEventBus(final EventBus eventBus) {
    super.setEventBus(eventBus, this, EVENT_BINDER);
  }
}
