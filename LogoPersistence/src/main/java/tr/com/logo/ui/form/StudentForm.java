package tr.com.logo.ui.form;

import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MFormLayout;

import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;

import tr.com.logo.domain.Student;
import tr.com.logo.ui.select.ClassroomComboBox;

public class StudentForm extends AbstractForm<Student> {

	private TextField name = new MTextField("Ad").withNullRepresentation("");
	private TextField surname = new MTextField("Soyad").withNullRepresentation("");
	private TextField studentId = new MTextField("Öğrenci No").withNullRepresentation("");
	private ClassroomComboBox classroom = new ClassroomComboBox("Sınıf");

	public StudentForm() {
	}
	@Override
	protected Component createContent() {
		return new MFormLayout(name, surname, studentId, classroom, getToolbar())
				.withMargin(true);
	}



}
