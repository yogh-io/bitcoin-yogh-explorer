package nl.yogh.wui.explorer;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.gwt.place.shared.PlaceHistoryHandler.Historian;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.web.bindery.event.shared.EventBus;

import nl.aerius.wui.activity.ActivityManager;
import nl.aerius.wui.activity.ActivityMapper;
import nl.aerius.wui.dev.DevelopmentObserver;
import nl.aerius.wui.event.CommandEventBus;
import nl.aerius.wui.history.HTML5Historian;
import nl.aerius.wui.history.PlaceHistoryMapper;
import nl.aerius.wui.place.ApplicationPlace;
import nl.aerius.wui.place.DefaultPlace;
import nl.aerius.wui.vue.AcceptsOneComponent;
import nl.aerius.wui.vue.activity.VueActivityManager;
import nl.yogh.wui.explorer.component.hex.color.BlockColors;
import nl.yogh.wui.explorer.component.hex.color.SimpleColorPicker;
import nl.yogh.wui.explorer.daemon.DaemonBootstrapper;
import nl.yogh.wui.explorer.daemon.ExplorerDaemonBootstrapper;
import nl.yogh.wui.explorer.dev.ExplorerDevelopmentObserver;
import nl.yogh.wui.explorer.factory.ExplorerActivityFactory;
import nl.yogh.wui.explorer.mapper.ExplorerActivityMapper;
import nl.yogh.wui.explorer.mapper.ExplorerPlaceHistoryMapper;
import nl.yogh.wui.explorer.place.ExplorerPlaces.LandingPlace;
import nl.yogh.wui.explorer.ui.MainActivityFactory;

public class ApplicationClientModule extends AbstractGinModule {
  @Override
  protected void configure() {
    bind(EventBus.class).to(CommandEventBus.class).in(Singleton.class);

    bind(ApplicationPlace.class).annotatedWith(DefaultPlace.class).to(LandingPlace.class);
    bind(Historian.class).to(HTML5Historian.class);
    
    bind(BlockColors.class).to(SimpleColorPicker.class);

    bind(new TypeLiteral<ActivityMapper<AcceptsOneComponent>>() {}).to(ExplorerActivityMapper.class);
    bind(new TypeLiteral<ActivityManager<AcceptsOneComponent>>() {}).to(VueActivityManager.class);
    bind(PlaceHistoryMapper.class).to(ExplorerPlaceHistoryMapper.class);
    bind(DaemonBootstrapper.class).to(ExplorerDaemonBootstrapper.class);
    bind(DevelopmentObserver.class).to(ExplorerDevelopmentObserver.class);

    install(new GinFactoryModuleBuilder().build(ExplorerActivityFactory.class));
    install(new GinFactoryModuleBuilder().build(MainActivityFactory.class));
  }
}
