package nl.yogh.wui.explorer.component.links;

import javax.inject.Inject;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Computed;
import com.axellience.vuegwt.core.annotations.component.Prop;
import com.axellience.vuegwt.core.client.component.IsVueComponent;
import com.google.web.bindery.event.shared.EventBus;

import jsinterop.annotations.JsMethod;

import nl.yogh.wui.explorer.command.LoadBlockCommand;
import nl.yogh.wui.explorer.place.BlockPlace;

@Component
public class BlockLink implements IsVueComponent {
  @Prop String hash;

  @Inject EventBus eventBus;

  @JsMethod
  public void go() {
    eventBus.fireEvent(new LoadBlockCommand(hash));
  }

  @Computed
  public String getToken() {
    return new BlockPlace(hash).getToken();
  }
}
