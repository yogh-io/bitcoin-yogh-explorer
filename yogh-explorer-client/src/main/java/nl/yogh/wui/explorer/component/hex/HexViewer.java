package nl.yogh.wui.explorer.component.hex;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Data;
import com.axellience.vuegwt.core.annotations.component.Prop;
import com.axellience.vuegwt.core.annotations.component.PropDefault;
import com.axellience.vuegwt.core.annotations.component.Watch;
import com.axellience.vuegwt.core.client.component.IsVueComponent;
import com.googlecode.gwt.crypto.bouncycastle.util.encoders.Hex;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;

@Component
public class HexViewer implements IsVueComponent {
  @Prop String hex;

  @Prop InterpretationStrategy strategy;

  @Data @JsProperty Part[] parts;

  @PropDefault("strategy")
  InterpretationStrategy defaultStrategy() {
    return new SimpleBlandStrategy();
  }

  @Watch(value = "hex", isImmediate = true)
  public void onHexChange() {
    parts = strategy.interpret(hex);
  }

  @JsMethod
  public String getHex(final byte bite) {
    return new String(Hex.encode(new byte[] { bite })).toUpperCase();
  }
}