package tr.com.logo.ui.view;

import com.vaadin.navigator.View;

import tr.com.logo.ui.navigation.ViewNames;

public enum ViewContainer {
	STUDENTS(ViewNames.STUDENTS, StudentsView.class),
	CLASSROOMS(ViewNames.CLASSROOMS, ClassroomView.class),
	DEPARTMENTS(ViewNames.DEPARTMENTS, DepartmentView.class);

	private String uriFragment;
	private Class<? extends View> viewClass;

	private ViewContainer(String uriFragment, Class<? extends View> viewClass) {
		this.uriFragment = uriFragment;
		this.viewClass = viewClass;
	}
	public String getUriFragment() {
		return uriFragment;
	}
	public Class<? extends View> getViewClass() {
		return viewClass;
	}


}
