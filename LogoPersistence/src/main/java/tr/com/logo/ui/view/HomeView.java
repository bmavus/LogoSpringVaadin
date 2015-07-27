package tr.com.logo.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;

public class HomeView extends VerticalLayout implements View {

	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Entered Error View", Type.ERROR_MESSAGE);
	}

}
