package nl.yogh.wui.explorer.context;

import javax.inject.Singleton;

import com.axellience.vuegwt.core.annotations.component.Data;

import nl.yogh.wui.explorer.service.domain.AddressInformation;

@Singleton
public class AddressContext {
  @Data public AddressInformation addressInformation = null;
  @Data public Throwable failure = null;
  @Data public boolean addressLoading;

  public void clear() {
    addressInformation = null;
    failure = null;
  }

  public void setFailure(final Throwable e) {
    clear();
    failure = e;
  }

  public void setAddressInformation(final AddressInformation addressInformation) {
    addressLoading = false;
    this.addressInformation = addressInformation;
  }

  public void setLoading() {
    addressLoading = true;
  }
}
