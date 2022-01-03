/*
 * Copyright the State of the Netherlands
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */
package nl.yogh.wui.component;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Prop;
import com.axellience.vuegwt.core.annotations.component.PropDefault;
import com.axellience.vuegwt.core.client.Vue;
import com.axellience.vuegwt.core.client.component.IsVueComponent;

import elemental2.dom.Element;
import elemental2.dom.HTMLElement;

import jsinterop.annotations.JsMethod;

import nl.aerius.wui.util.ComputedStyleUtil;

@Component
public class HorizontalCollapse implements IsVueComponent {
  @Prop Boolean seek = true;

  @PropDefault("seek")
  Boolean seekDefault() {
      return true;
  }

  @JsMethod
  public void enter(final Element el) {
    // If seek is true (default), set element width to its natural width, implying it is not already set (i.e. through css)
    if (seek) {
      setWidth(el, el.scrollWidth);
    }
  }

  @JsMethod
  public void afterEnter(final Element el) {
    clearWidth(el);
  }

  @JsMethod
  public void leave(final Element el) {
    setWidth(el, el.scrollWidth);
    ComputedStyleUtil.forceStyleRender(el);
    Vue.nextTick(() -> setWidth(el, 0));
  }

  @JsMethod
  public void afterLeave(final Element el) {
    clearWidth(el);
  }

  private void setWidth(final Element el, final int width) {
    ((HTMLElement) el).style.set("width", String.valueOf(width) + "px");
  }

  private void clearWidth(final Element el) {
    ((HTMLElement) el).style.removeProperty("width");
  }
}
