package nl.yogh.wui.explorer.ui.landing;

import javax.inject.Inject;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Data;
import com.axellience.vuegwt.core.annotations.component.Prop;
import com.axellience.vuegwt.core.client.component.IsVueComponent;
import com.axellience.vuegwt.core.client.component.hooks.HasCreated;
import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;

import jsinterop.annotations.JsMethod;

import nl.aerius.wui.place.PlaceController;
import nl.yogh.wui.explorer.component.color.ColorPicker;
import nl.yogh.wui.explorer.component.fields.ColorField;
import nl.yogh.wui.explorer.component.fields.LabeledValue;
import nl.yogh.wui.explorer.component.links.AddressLink;
import nl.yogh.wui.explorer.component.links.BlockLink;
import nl.yogh.wui.explorer.component.links.TransactionLink;
import nl.yogh.wui.explorer.component.misc.LoadingHeading;
import nl.yogh.wui.explorer.context.OverviewContext;
import nl.yogh.wui.explorer.place.MempoolPlace;
import nl.yogh.wui.explorer.ui.BitcoinUtilityComponent;

@Component(components = {
    BlockLink.class,
    TransactionLink.class,
    AddressLink.class,
    ColorField.class,
    LabeledValue.class,
    LoadingHeading.class
})
public class LandingView extends BitcoinUtilityComponent implements IsVueComponent, HasCreated {
  private static final LandingViewEventBinder EVENT_BINDER = GWT.create(LandingViewEventBinder.class);

  interface LandingViewEventBinder extends EventBinder<LandingView> {}

  @Prop EventBus eventBus;

  @Inject @Data OverviewContext context;

  @Inject PlaceController placeController;

  @Data @Inject ColorPicker picker;

  @JsMethod
  public void viewMempool() {
    placeController.goTo(new MempoolPlace());
  }

  @Override
  public void created() {
    EVENT_BINDER.bindEventHandlers(this, eventBus);
  }

  public static native void tippy() /*-{
                                    tippy('[data-tippy-content]');    
                                    }-*/;
}
