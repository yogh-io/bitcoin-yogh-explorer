package nl.yogh.wui.bootstrap;

import java.util.Date;

import com.axellience.vuegwt.core.client.Vue;
import com.axellience.vuegwt.core.client.VueGWT;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.core.client.Scheduler;

import nl.aerius.wui.dev.GWTProd;
import nl.yogh.wui.Application;
import nl.yogh.wui.component.material.MaterialIconFactory;

public class VueBootstrap implements EntryPoint {
  @Override
  public void onModuleLoad() {
    final long startTime = new Date().getTime();

    GWTProd.info("Hello! Welcome to the YOGH Explorer Console!");

    VueGWT.init();
    VueGWT.onReady(() -> {
      initVueLibraries();
      registerComponents();

      GWTProd.info("Vue initialised in " + (new Date().getTime() - startTime) + "ms");
      final BootstrapLoadingView comp = BootstrapLoadingViewFactory.get().create();
      comp.vue().$mount("#bootstrap");
      GWT.runAsync(new RunAsyncCallback() {
        @Override
        public void onFailure(final Throwable caught) {
          GWTProd.error("Bootstrapper failed. " + caught.getMessage());
        }

        @Override
        public void onSuccess() {
          GWTProd.info("Bootstrapper initialised in " + (new Date().getTime() - startTime) + "ms");

          Scheduler.get().scheduleFinally(() -> {
            comp.onApplicationReady(() -> {
              Application.A.create(() -> {
                comp.destroy();
              });
            });
            GWTProd.info("Application loaded in " + (new Date().getTime() - startTime) + "ms");
            GWTProd.log("");
          });
        }
      });
    });
  }

  private void registerComponents() {
    Vue.component(MaterialIconFactory.get());

    //Vue.getConfig().addIgnoredElement("mempool-histogram");
  }

  private void initVueLibraries() {
    // No-op for now
  }
}
