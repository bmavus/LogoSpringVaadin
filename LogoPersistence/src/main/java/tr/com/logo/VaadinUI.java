package tr.com.logo;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

import tr.com.logo.service.ClassroomService;
import tr.com.logo.service.DepartmentService;
import tr.com.logo.service.StudentService;
import tr.com.logo.ui.eventbus.LogoEventBus;
import tr.com.logo.ui.layout.RootLayout;

@Theme("valo")
@Title("Logo Persistence Demo")
@Widgetset("tr.com.logo.LogoWidgetSet")
@SpringUI(path = "logo")
public class VaadinUI extends UI {

	@Autowired
	private StudentService studentService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private ClassroomService classroomService;

	@Autowired
	private LogoEventBus eventBus;

	public LogoEventBus getEventBus() {
		return eventBus;
	}

	@Override
	protected void init(VaadinRequest request) {
		eventBus.register(this);
		RootLayout rootLayout = new RootLayout();
		setContent(rootLayout);
	}

	public static VaadinUI get() {
		return (VaadinUI) UI.getCurrent();
	}

	public StudentService getStudentService() {
		return studentService;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public ClassroomService getClassroomService() {
		return classroomService;
	}


}
