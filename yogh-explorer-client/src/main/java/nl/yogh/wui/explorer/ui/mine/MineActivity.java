package nl.yogh.wui.explorer.ui.mine;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.inject.assistedinject.Assisted;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;

import nl.aerius.wui.event.BasicEventComponent;
import nl.yogh.wui.explorer.ui.MainView;

public class MineActivity extends BasicEventComponent implements MinePresenter {
  private static final MineActivityEventBinder EVENT_BINDER = GWT.create(MineActivityEventBinder.class);

  interface MineActivityEventBinder extends EventBinder<MineActivity> {}

  @Inject
  public MineActivity(@Assisted final MainView view) {
    view.setMain(MineViewFactory.get());
  }

  @Override
  public void onStart() {}

  @Override
  public void onStop() {}

  @Override
  public void setView(final MineView view) {}

  @Override
  public void setEventBus(final EventBus eventBus) {
    super.setEventBus(eventBus, this, EVENT_BINDER);
  }
}
