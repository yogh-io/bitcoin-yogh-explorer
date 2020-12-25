package nl.yogh.wui.component.material;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.client.component.IsVueComponent;

import jsinterop.annotations.JsMethod;

@Component(name = "mat-icon")
public class MaterialIcon implements IsVueComponent {
  @JsMethod
  public void emitClick() {
    vue().$emit("click");
  }
}
