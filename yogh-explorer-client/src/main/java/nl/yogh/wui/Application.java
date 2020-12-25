package nl.yogh.wui;

import com.google.gwt.core.client.GWT;

/**
 * @implNote For some reason, during development, it might help to modify, save,
 *           and rebuild this class in order for deferred binding to work.
 * 
 *           Noted here because this is inevitably where you'll end up during
 *           debugging.
 *           
 */
public class Application {
  public static final Application A = GWT.create(Application.class);

  public void create(final Runnable finish) {
    throw new RuntimeException("No Application implementation injected asdfasdf.");
  }
}
