package nl.yogh.wui.explorer.component.fields;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Prop;
import com.axellience.vuegwt.core.client.component.IsVueComponent;

@Component
public class SimpleField implements IsVueComponent {
  @Prop String label;
  @Prop String value;
}
