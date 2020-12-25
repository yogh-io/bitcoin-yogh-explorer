package nl.yogh.wui.explorer.context;

import javax.inject.Singleton;

@Singleton
public class ConfigurationContext {
  private String source;

  public void setSource(final String source) {
    this.source = source;
  }

  public String getSource() {
    return source;
  }
}
