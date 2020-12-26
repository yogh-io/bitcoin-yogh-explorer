package nl.yogh.wui.explorer.config;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "AppConfig")
public class EnvironmentConfigurationWrapper {
  @JsProperty public String buildNumber;
}