package nl.yogh.wui.explorer.component.fields;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Computed;
import com.axellience.vuegwt.core.annotations.component.Prop;
import com.axellience.vuegwt.core.annotations.component.PropDefault;
import com.axellience.vuegwt.core.client.component.IsVueComponent;

import nl.yogh.wui.explorer.component.color.Color;

@Component
public class ColorField implements IsVueComponent {
  @Prop String label;
  @Prop String value;

  @Prop Color color;

  @PropDefault("value")
  String valueDefault() {
    return "";
  }

  @PropDefault("color")
  Color colorDefault() {
    return new Color(128, 128, 128);
  }

  @Computed
  public String getBackcolor() {
    final Color back = color.copy();
    back.setA(0.2);

    return back.getValue();
  }

  @Computed
  public String getForecolor() {
    final Color fore = color.copy();
    fore.setA(0.8);
    return fore.getValue();
  }
}
