package nl.yogh.wui.explorer.ui.address;

import javax.inject.Inject;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Data;
import com.axellience.vuegwt.core.annotations.component.Prop;
import com.axellience.vuegwt.core.client.component.IsVueComponent;
import com.google.web.bindery.event.shared.EventBus;

import nl.yogh.wui.explorer.component.color.ColorPicker;
import nl.yogh.wui.explorer.component.fields.ColorField;
import nl.yogh.wui.explorer.component.fields.LabeledValue;
import nl.yogh.wui.explorer.component.links.AddressLink;
import nl.yogh.wui.explorer.component.links.BlockLink;
import nl.yogh.wui.explorer.component.links.TransactionLink;
import nl.yogh.wui.explorer.component.misc.LoadingHeading;
import nl.yogh.wui.explorer.context.AddressContext;

@Component(components = {
    BlockLink.class,
    TransactionLink.class,
    AddressLink.class,
    LabeledValue.class,
    ColorField.class,
    LoadingHeading.class
})
public class AddressView implements IsVueComponent {
  @Prop EventBus eventBus;

  @Data @Inject AddressContext context;

  @Data @Inject ColorPicker picker;

}
