package nl.yogh.wui.component;

import com.axellience.vuegwt.core.annotations.directive.Directive;
import com.axellience.vuegwt.core.client.directive.VueDirective;
import com.axellience.vuegwt.core.client.vnode.VNode;
import com.axellience.vuegwt.core.client.vnode.VNodeDirective;
import com.google.gwt.resources.client.DataResource;

import elemental2.dom.CSSStyleDeclaration;
import elemental2.dom.Element;
import elemental2.dom.HTMLElement;

/**
 * Example usage:
 *
 * <pre>
 * &lt;div v-mask="img.exampleIcon()" &#47;&gt;
 * </pre>
 */
@Directive
public class MaskDirective extends VueDirective {
  @Override
  public void inserted(final Element el, final VNodeDirective binding, final VNode vnode) {
    super.inserted(el, binding, vnode);

    final String img = ((DataResource) binding.getValue()).getSafeUri().asString();
    final CSSStyleDeclaration style = ((HTMLElement) el).style;

    style.setProperty("mask", "url(" + img + ")");
    style.setProperty("mask-image", "url(" + img + ")");
    style.setProperty("-webkit-mask", "url(" + img + ")");
    style.setProperty("-webkit-mask-image", "url(" + img + ")");
  }
}
