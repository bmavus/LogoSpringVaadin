package tr.com.logo.ui.form;

import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MFormLayout;

import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;

import tr.com.logo.domain.Department;

public class DepartmentForm extends AbstractForm<Department> {

	private TextField name = new TextField("Bölüm Adı");

	@Override
	protected Component createContent() {
		return new MFormLayout(name, getToolbar())
				.withMargin(true);
	}

}
