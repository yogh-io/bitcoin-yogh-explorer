package nl.yogh.wui.explorer.component.links;

import javax.inject.Inject;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Computed;
import com.axellience.vuegwt.core.annotations.component.Prop;
import com.axellience.vuegwt.core.client.component.IsVueComponent;
import com.google.web.bindery.event.shared.EventBus;

import jsinterop.annotations.JsMethod;

import nl.yogh.wui.explorer.place.TransactionPlace;

@Component
public class TransactionLink implements IsVueComponent {
  @Prop String hash;

  @Inject EventBus eventBus;

  @JsMethod
  public void go() {
    eventBus.fireEvent(new LoadTransactionCommand(hash));
  }

  @Computed
  public String getToken() {
    return new TransactionPlace(hash).getToken();
  }
}
