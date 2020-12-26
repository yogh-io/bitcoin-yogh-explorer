package nl.yogh.wui.explorer.config;

import com.google.inject.ImplementedBy;

@ImplementedBy(EnvironmentConfigurationImpl.class)
public interface EnvironmentConfiguration {
  String getBuildNumber();
}
