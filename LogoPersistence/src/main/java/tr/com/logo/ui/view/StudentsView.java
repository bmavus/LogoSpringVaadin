package tr.com.logo.ui.view;

import org.vaadin.viritin.fields.MTable;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import tr.com.logo.VaadinUI;
import tr.com.logo.domain.Student;
import tr.com.logo.service.StudentService;
import tr.com.logo.ui.form.StudentForm;
import tr.com.logo.ui.table.StudentTable;

public class StudentsView extends VerticalLayout implements View {

	private Button createButton, editButton;
	private MTable<Student> studentTable;
	private StudentService studentService;

	public StudentsView() {
		studentService = VaadinUI.get().getStudentService();
		setSpacing(true);
		addComponent(createButtonLayout());
		addComponent(createTable());
	}

	private HorizontalLayout createButtonLayout() {
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setSpacing(true);
		//		buttonLayout.setMargin(new MarginInfo(false, false, false, true));

		createButton = new Button("Yeni Öğrenci");

		createButton.addClickListener(event -> {
			StudentForm studentForm = new StudentForm();
			studentForm.setEntity(new Student());
			Window popup = studentForm.openInModalPopup();
			studentForm.setSavedHandler(entity -> {
				studentService.save(entity);
				studentTable.addItem(entity);
				popup.close();
				studentTable.select(entity);
				studentTable.setCurrentPageFirstItemId(entity);
			});
		});

		editButton = new Button("Öğrenci Düzenle");
		editButton.addClickListener(event -> {
			StudentForm studentForm = new StudentForm();
			studentForm.setEntity(studentTable.getValue());
			for(Object id : studentForm.getFieldGroup().getBoundPropertyIds()) {
				System.out.println(id);
			}
			for(Field f : studentForm.getFieldGroup().getFields()) {
				System.out.println(f.getValue());
			}
			Window popup = studentForm.openInModalPopup();
			studentForm.setSavedHandler(entity -> {
				studentService.save(entity);
				popup.close();
				studentTable.select(entity);
				studentTable.setCurrentPageFirstItemId(entity);
			});
		});

		buttonLayout.addComponent(createButton);
		buttonLayout.addComponent(editButton);

		return buttonLayout;
	}

	private Table createTable() {
		studentTable = new StudentTable();
		studentTable.setSelectable(true);
		return studentTable;
	}
	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Student View", Type.ERROR_MESSAGE);
	}

}
