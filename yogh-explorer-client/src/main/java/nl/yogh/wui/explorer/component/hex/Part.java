package nl.yogh.wui.explorer.component.hex;

import java.util.List;

import com.axellience.vuegwt.core.annotations.component.Data;

import jsinterop.annotations.JsProperty;

public class Part {
  public Part(final List<Byte> bytes, final String backcolor, final String forecolor) {
    this.bytes = bytes;
    this.backcolor = backcolor;
    this.forecolor = forecolor;
  }

  @Data @JsProperty public List<Byte> bytes;

  @Data public String backcolor;
  @Data public String forecolor;
}
