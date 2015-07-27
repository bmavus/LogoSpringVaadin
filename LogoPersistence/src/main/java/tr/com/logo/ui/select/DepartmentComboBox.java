package tr.com.logo.ui.select;

import java.util.List;

import org.vaadin.viritin.fields.TypedSelect;

import com.vaadin.ui.ComboBox;

import tr.com.logo.VaadinUI;
import tr.com.logo.domain.Department;

public class DepartmentComboBox extends TypedSelect<Department> {

	public DepartmentComboBox(String caption) {
		super(Department.class);
		withCaption(caption);
		withSelectType(ComboBox.class);
		List<Department> departments =
				VaadinUI.get().getDepartmentService().getAllDepartments();
		if(departments != null) {
			setOptions(departments);
		}
		setCaptionGenerator(option -> option.getName());
	}
}
