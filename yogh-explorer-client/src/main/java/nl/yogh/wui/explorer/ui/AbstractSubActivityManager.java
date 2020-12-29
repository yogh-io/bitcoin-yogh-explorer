/*
 * Copyright the State of the Netherlands
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */
package nl.yogh.wui.explorer.ui;

import java.util.function.Consumer;

import com.google.web.bindery.event.shared.ResettableEventBus;

import nl.aerius.wui.event.HasEventBus;
import nl.aerius.wui.place.Place;

public abstract class AbstractSubActivityManager<V, S extends SubActivity> {
  private ResettableEventBus eventBus;

  private Consumer<Place> redirector;

  private V view;
  private Place place;

  private boolean delegate;

  private S currentActivity;

  public boolean delegate(final ResettableEventBus eventBus, final Place place, final Consumer<Place> redirector) {
    this.eventBus = eventBus;
    this.place = place;
    this.redirector = redirector;

    return tryCanDelegate();
  }

  public void setView(final V view) {
    this.view = view;

    doDelegate();
  }

  private void doDelegate() {
    if (!delegate) {
      return;
    }

    delegate = false;
    final S act = getActivity(place, view);
    if (act == null) {
      return;
    }
    
    if (currentActivity != null) {
      currentActivity.onStop();
    }
    eventBus.removeHandlers();

    if (act instanceof HasEventBus) {
      ((HasEventBus) act).setEventBus(eventBus);
    }
    act.onStart();
    currentActivity = act;
  }

  private S redirect(final Place place) {
    redirector.accept(place);
    return null;
  }

  private boolean tryCanDelegate() {
    final Place redirect = getRedirect(place);

    if (redirect == null) {
      scheduleDelegate();
    } else {
      redirect(redirect);
    }

    return redirect == null;
  }

  private void scheduleDelegate() {
    delegate = true;
    // Will delegate if the view is not null (which is unlikely on start up for example)
    if (view != null) {
      doDelegate();
    }
  }

  protected abstract Place getRedirect(Place place);

  protected abstract S getActivity(Place place, V view);
}
