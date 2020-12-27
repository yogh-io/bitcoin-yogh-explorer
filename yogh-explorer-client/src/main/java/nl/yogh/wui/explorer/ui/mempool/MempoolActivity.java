package nl.yogh.wui.explorer.ui.mempool;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.inject.assistedinject.Assisted;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import nl.aerius.wui.event.BasicEventComponent;
import nl.yogh.wui.explorer.command.LoadMempoolCommand;
import nl.yogh.wui.explorer.event.SourceChangedEvent;
import nl.yogh.wui.explorer.ui.MainView;

public class MempoolActivity extends BasicEventComponent implements MempoolPresenter {
  private static final MempoolActivityEventBinder EVENT_BINDER = GWT.create(MempoolActivityEventBinder.class);

  interface MempoolActivityEventBinder extends EventBinder<MempoolActivity> {}

  @Inject
  public MempoolActivity(@Assisted final MainView view) {
    view.setMain(MempoolViewFactory.get());
  }

  @Override
  public void onStart() {
    eventBus.fireEvent(new LoadMempoolCommand());
  }

  @EventHandler
  public void onSourceChangedEvent(final SourceChangedEvent c) {
    eventBus.fireEvent(new LoadMempoolCommand());
  }

  @Override
  public void setView(final MempoolView view) {}

  @Override
  public void setEventBus(final EventBus eventBus) {
    super.setEventBus(eventBus, this, EVENT_BINDER);
  }
}
