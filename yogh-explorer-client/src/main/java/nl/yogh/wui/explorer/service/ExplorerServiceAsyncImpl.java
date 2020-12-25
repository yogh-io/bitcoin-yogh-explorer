package nl.yogh.wui.explorer.service;

import javax.inject.Inject;

import com.google.inject.Singleton;

import nl.yogh.wui.explorer.config.EnvironmentConfiguration;

@Singleton
public class ExplorerServiceAsyncImpl implements ExplorerServiceAsync {
  @Inject EnvironmentConfiguration cfg;
}
