package nl.yogh.wui.explorer.ui.mempool;

import javax.inject.Inject;

import com.google.inject.assistedinject.Assisted;

import nl.aerius.wui.event.BasicEventComponent;
import nl.yogh.wui.explorer.place.MempoolPlace;
import nl.yogh.wui.explorer.ui.MainView;

public class MempoolActivity extends BasicEventComponent implements MempoolPresenter {
  private final MempoolPlace place;

  @Inject
  public MempoolActivity(@Assisted final MainView view, @Assisted final MempoolPlace place) {
    this.place = place;
    view.setMain(MempoolViewFactory.get());
  }

  @Override
  public void onStart() {}

  @Override
  public void setView(final MempoolView view) {}
}
