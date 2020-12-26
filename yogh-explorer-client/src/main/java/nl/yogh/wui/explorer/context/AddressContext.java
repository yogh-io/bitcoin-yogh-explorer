package nl.yogh.wui.explorer.context;

import javax.inject.Singleton;

import com.axellience.vuegwt.core.annotations.component.Data;

import jsinterop.annotations.JsProperty;

import nl.yogh.wui.explorer.service.domain.AddressInformation;
import nl.yogh.wui.explorer.service.domain.UtxoInformation;

@Singleton
public class AddressContext {
  @Data public AddressInformation addressInformation = null;
  @Data @JsProperty public UtxoInformation[] utxos = null;

  @Data public Throwable failure = null;
  @Data public boolean addressLoading;
  @Data public boolean utxosLoading;

  public void clear() {
    addressInformation = null;
    utxos = null;
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

  public void setUtxos(final UtxoInformation[] utxos) {
    utxosLoading = false;
    this.utxos = utxos;
  }

  public void setLoading() {
    addressLoading = true;
  }
}
