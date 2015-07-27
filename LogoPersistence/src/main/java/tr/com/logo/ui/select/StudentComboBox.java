package tr.com.logo.ui.select;

import org.vaadin.viritin.fields.TypedSelect;

import com.vaadin.ui.ComboBox;

import tr.com.logo.VaadinUI;
import tr.com.logo.domain.Student;

public class StudentComboBox extends TypedSelect<Student> {

	public StudentComboBox(String caption) {
		super(Student.class);
		withCaption(caption);
		withSelectType(ComboBox.class);
		setOptions(VaadinUI.get().getStudentService().getAllStudents());
	}
}
