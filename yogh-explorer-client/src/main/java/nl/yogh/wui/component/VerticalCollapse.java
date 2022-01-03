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
import com.axellience.vuegwt.core.client.Vue;
import com.axellience.vuegwt.core.client.component.IsVueComponent;

import elemental2.dom.Element;
import elemental2.dom.HTMLElement;

import jsinterop.annotations.JsMethod;

import nl.aerius.wui.util.ComputedStyleUtil;

@Component
public class VerticalCollapse implements IsVueComponent {
  @Prop String target;

  @JsMethod
  public void enter(final Element el) {
    setHeight(el, ((HTMLElement) el).scrollHeight);
  }

  @JsMethod
  public void afterEnter(final Element el) {
    clearHeight(el);
  }

  @JsMethod
  public void leave(final Element el) {
    setHeight(el, ((HTMLElement) el).scrollHeight);
    ComputedStyleUtil.forceStyleRender(el);
    Vue.nextTick(() -> setHeight(el, 0));
  }

  @JsMethod
  public void afterLeave(final Element el) {
    clearHeight(el);
  }

  private void setHeight(final Element el, final int height) {
    if (height > 0) {
      ((HTMLElement) el).style.set("height", (target == null ? String.valueOf(height) : target) + "px");
    } else {
      ((HTMLElement) el).style.set("height", String.valueOf(height) + "px");
    }
  }

  private void clearHeight(final Element el) {
    ((HTMLElement) el).style.removeProperty("height");
  }
}
