package nl.yogh.wui.explorer.mapper;

import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;

import nl.aerius.wui.activity.Activity;
import nl.aerius.wui.activity.ActivityMapper;
import nl.aerius.wui.place.ApplicationPlace;
import nl.aerius.wui.place.DefaultPlace;
import nl.aerius.wui.place.Place;
import nl.aerius.wui.vue.AcceptsOneComponent;
import nl.yogh.wui.explorer.factory.ExplorerActivityFactory;
import nl.yogh.wui.explorer.place.ExplorerPlaces.MainPlace;

public class ExplorerActivityMapper implements ActivityMapper<AcceptsOneComponent> {
  private final ExplorerActivityFactory factory;

  @Inject
  public ExplorerActivityMapper(@DefaultPlace final ApplicationPlace place, final ExplorerActivityFactory factory) {
    this.factory = factory;
  }

  @Override
  public Activity<?, AcceptsOneComponent> getActivity(final Place place) {
    Activity<?, AcceptsOneComponent> presenter = null;

    presenter = tryGetActivity(place);

    if (presenter == null) {
      GWT.log("Presenter is null: Place ends up nowhere. " + place);
      throw new RuntimeException("No Presenter found for place " + place);
    }

    return presenter;
  }

  private Activity<?, AcceptsOneComponent> tryGetActivity(final Place place) {
    Activity<?, AcceptsOneComponent> presenter = null;

    if (place instanceof MainPlace) {
      presenter = factory.createMainPresenter((MainPlace) place);
    }
    return presenter;
  }
}
