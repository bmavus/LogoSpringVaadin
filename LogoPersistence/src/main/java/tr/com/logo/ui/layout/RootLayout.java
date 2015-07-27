package tr.com.logo.ui.layout;

import com.google.common.eventbus.Subscribe;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import tr.com.logo.VaadinUI;
import tr.com.logo.ui.eventbus.Events;
import tr.com.logo.ui.navigation.LogoNavigator;
import tr.com.logo.ui.navigation.ViewNames;

public class RootLayout extends HorizontalLayout {

	private CustomButton viewStudentsButton;
	private CustomButton viewClassroomsButton;
	private CustomButton viewDepartmentsButton;

	public RootLayout() {
		VaadinUI.get().getEventBus().register(this);
		setMargin(true);
		setSpacing(true);
		setSizeFull();

		VerticalLayout accordionLayout = createAccordionLayout();
		VerticalLayout navigationLayout = new VerticalLayout();

		addComponent(accordionLayout);
		addComponent(navigationLayout);

		setExpandRatio(accordionLayout, 1.0f);
		setExpandRatio(navigationLayout, 3.0f);

		new LogoNavigator(navigationLayout);
	}

	private VerticalLayout createAccordionLayout() {
		Accordion accordion = new Accordion();

		viewStudentsButton = new CustomButton("Öğrenciler", ViewNames.STUDENTS);
		viewStudentsButton.setStyleName(ValoTheme.BUTTON_LINK);
		viewStudentsButton.addClickListener(event -> {
			UI.getCurrent().getNavigator().navigateTo(ViewNames.STUDENTS);
		});

		viewClassroomsButton = new CustomButton("Sınıflar", ViewNames.CLASSROOMS);
		viewClassroomsButton.setStyleName(ValoTheme.BUTTON_LINK);
		viewClassroomsButton.addClickListener(event -> {
			UI.getCurrent().getNavigator().navigateTo(ViewNames.CLASSROOMS);
		});

		viewDepartmentsButton = new CustomButton("Bölümler", ViewNames.DEPARTMENTS);
		viewDepartmentsButton.setStyleName(ValoTheme.BUTTON_LINK);
		viewDepartmentsButton.addClickListener(event -> {
			UI.getCurrent().getNavigator().navigateTo(ViewNames.DEPARTMENTS);
		});
		accordion.addTab(viewStudentsButton, "Öğrenci İşleri");
		accordion.addTab(viewClassroomsButton, "Sınıf İşleri");
		accordion.addTab(viewDepartmentsButton, "Bölüm İşleri");

		return new VerticalLayout(accordion);
	}

	private static class CustomButton extends Button {

		private String caption;
		private String viewName;

		public CustomButton(String caption, String viewName) {
			super(caption);
			this.viewName = viewName;
		}

		@Override
		public String getCaption() {
			return caption;
		}

		public String getViewName() {
			return viewName;
		}

	}

	@Subscribe
	public void stateChanged(Events.ViewAlteredEvent event) {
		String navigationState = event.getNavigationState();
		if(navigationState.equals(viewStudentsButton.getViewName())) {
			viewStudentsButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		}
		else if(navigationState.equals(viewClassroomsButton.getViewName())) {
			viewClassroomsButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		}
		else if(navigationState.equals(viewDepartmentsButton.getViewName())) {
			viewDepartmentsButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		}
	}
}
