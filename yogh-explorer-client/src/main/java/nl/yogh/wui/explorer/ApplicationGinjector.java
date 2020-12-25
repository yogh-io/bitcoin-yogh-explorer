package nl.yogh.wui.explorer;

import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

import nl.yogh.wui.ApplicationImpl;

@GinModules(value = ApplicationClientModule.class, properties = "application.ginModules")
public interface ApplicationGinjector extends Ginjector {
  /**
   * Global injector instance.
   */
  ApplicationGinjector INSTANCE = GWT.create(ApplicationGinjector.class);

  void inject(ApplicationImpl applicationRoot);
}
