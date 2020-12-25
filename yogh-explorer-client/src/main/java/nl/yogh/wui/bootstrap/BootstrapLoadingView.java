package nl.yogh.wui.bootstrap;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.client.component.IsVueComponent;

@Component
public class BootstrapLoadingView implements IsVueComponent {
  public void onApplicationReady(final Runnable runner) {
    runner.run();
  }

  public void destroy() {
    vue().$destroy();
    vue().$el().parentNode.removeChild(vue().$el());
  }
}
