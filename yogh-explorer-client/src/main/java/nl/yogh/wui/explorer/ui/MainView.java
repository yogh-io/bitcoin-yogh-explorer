package nl.yogh.wui.explorer.ui;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Data;
import com.axellience.vuegwt.core.annotations.component.Prop;
import com.axellience.vuegwt.core.client.component.IsVueComponent;
import com.axellience.vuegwt.core.client.component.hooks.HasActivated;
import com.axellience.vuegwt.core.client.vue.VueComponentFactory;
import com.google.web.bindery.event.shared.EventBus;

import nl.aerius.wui.dev.GWTProd;
import nl.yogh.wui.explorer.component.application.ApplicationView;
import nl.yogh.wui.explorer.ui.address.AddressView;
import nl.yogh.wui.explorer.ui.block.BlockView;
import nl.yogh.wui.explorer.ui.landing.LandingView;
import nl.yogh.wui.explorer.ui.transaction.TransactionView;

@Component(components = {
    ApplicationView.class,
    LandingView.class,
    TransactionView.class,
    BlockView.class,
    AddressView.class
})
public class MainView implements IsVueComponent, HasActivated {
  @Data String main;
  @Data MainSubPresenter sub;

  @Prop EventBus eventBus;

  @Prop MainPresenter presenter;

  public void setMain(final MainSubPresenter sub, final VueComponentFactory<?> fact) {
    this.sub = sub;
    setMain(fact);
  }

  public void setMain(final VueComponentFactory<?> fact) {
    main = fact.getComponentTagName();
  }

  @Override
  public void activated() {
    presenter.setView(this);

    GWTProd.log("Main bus: " + eventBus);
  }
}
