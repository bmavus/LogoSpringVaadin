package tr.com.logo.ui.form;

import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MFormLayout;

import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;

import tr.com.logo.domain.Classroom;
import tr.com.logo.ui.select.DepartmentComboBox;

public class ClassroomForm extends AbstractForm<Classroom> {

	private TextField name = new TextField("Şube Adı");
	private TextField description = new TextField("Tanım");
	private DepartmentComboBox department = new DepartmentComboBox("Bölüm");

	@Override
	protected Component createContent() {
		return new MFormLayout(name, description, department, getToolbar())
				.withMargin(true);
	}

}
