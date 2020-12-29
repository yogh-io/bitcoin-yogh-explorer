package nl.yogh.wui.explorer.component.misc;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Prop;
import com.axellience.vuegwt.core.client.component.IsVueComponent;

@Component(components = {
    HeadingComponent.class,
    LoadingComponent.class
})
public class LoadingHeading implements IsVueComponent {
  @Prop(required = true) String level;
  
  @Prop String text;

  @Prop(required = true) boolean loading;
}
