package nl.yogh.wui.explorer.context;

import javax.inject.Singleton;

import com.axellience.vuegwt.core.annotations.component.Data;

import nl.yogh.wui.explorer.service.domain.MempoolInformation;

@Singleton
public class MempoolContext {
  @Data public MempoolInformation pool = null;

  @Data public boolean mempoolLoading;

  @Data public Throwable failure = null;

  public void clear() {
    pool = null;
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
