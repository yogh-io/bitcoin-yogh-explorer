package nl.yogh.wui.explorer.service;

import elemental2.core.JsObject;

public class JSON {
  public static native JsObject parse(String str) /*-{
    return JSON.parse(str);
  }-*/;
}
