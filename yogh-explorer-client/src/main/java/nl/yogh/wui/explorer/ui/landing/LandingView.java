package nl.yogh.wui.explorer.ui.landing;

import javax.inject.Inject;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Data;
import com.axellience.vuegwt.core.annotations.component.Prop;
import com.axellience.vuegwt.core.client.component.IsVueComponent;
import com.google.web.bindery.event.shared.EventBus;

import jsinterop.annotations.JsMethod;

import nl.aerius.wui.place.PlaceController;
import nl.yogh.wui.explorer.component.links.AddressLink;
import nl.yogh.wui.explorer.component.links.BlockLink;
import nl.yogh.wui.explorer.component.links.TransactionLink;
import nl.yogh.wui.explorer.context.OverviewContext;
import nl.yogh.wui.explorer.place.MempoolPlace;

@Component(components = {
    BlockLink.class,
    TransactionLink.class,
    AddressLink.class
})
public class LandingView implements IsVueComponent {
  @Prop EventBus eventBus;

  @Inject @Data OverviewContext context;

  @Inject PlaceController placeController;

  @JsMethod
  public void viewMempool() {
    placeController.goTo(new MempoolPlace());
  }
}
