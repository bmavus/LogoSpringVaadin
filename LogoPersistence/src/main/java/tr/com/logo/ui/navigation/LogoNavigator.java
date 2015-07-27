package tr.com.logo.ui.navigation;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

import tr.com.logo.VaadinUI;
import tr.com.logo.ui.eventbus.Events;
import tr.com.logo.ui.eventbus.LogoEventBus;
import tr.com.logo.ui.view.HomeView;
import tr.com.logo.ui.view.ViewContainer;

public class LogoNavigator extends Navigator {

	private LogoEventBus eventBus;

	public LogoNavigator(ComponentContainer container) {
		super(UI.getCurrent(), container);
		eventBus = VaadinUI.get().getEventBus();
		initViewProviders();
		addViewChangeListener(new ViewChangeListener() {

			@Override
			public boolean beforeViewChange(ViewChangeEvent event) {
				return true;
			}

			@Override
			public void afterViewChange(ViewChangeEvent event) {
				eventBus.post(new Events.ViewAlteredEvent(getState()));
			}
		});
	}

	private void initViewProviders() {
		ViewProvider provider = null;
		for(ViewContainer viewContainer : ViewContainer.values()) {

			provider = new ClassBasedViewProvider(
					viewContainer.getUriFragment(),
					viewContainer.getViewClass()
					);

			addProvider(provider);
		}

		ViewProvider errorViewProvider = new ClassBasedViewProvider("",
				HomeView.class);
		setErrorProvider(errorViewProvider);
	}

}
