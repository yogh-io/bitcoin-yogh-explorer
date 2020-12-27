package nl.yogh.wui.explorer.context;

import javax.inject.Singleton;

import com.axellience.vuegwt.core.annotations.component.Data;

@Singleton
public class MempoolContext {
  @Data public Throwable failure = null;
  
  @Data public boolean mempoolLoading;

  public void clear() {
    failure = null;
  }

  public void setFailure(final Throwable e) {
    clear();
    failure = e;
  }

  public void setLoading() {
    mempoolLoading = true;
  }
}
