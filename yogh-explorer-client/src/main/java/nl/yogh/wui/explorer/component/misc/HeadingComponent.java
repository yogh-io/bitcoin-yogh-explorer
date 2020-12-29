package nl.yogh.wui.explorer.component.misc;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Prop;
import com.axellience.vuegwt.core.client.component.IsVueComponent;
import com.axellience.vuegwt.core.client.component.hooks.HasRender;
import com.axellience.vuegwt.core.client.vnode.VNode;
import com.axellience.vuegwt.core.client.vnode.builder.VNodeBuilder;

@Component(hasTemplate = false)
public class HeadingComponent implements IsVueComponent, HasRender {
  @Prop(required = true) String level;

  @Override
  public VNode render(final VNodeBuilder builder) {
    return builder.el("h" + this.level, vue().$slots().get("default"));
  }
}
