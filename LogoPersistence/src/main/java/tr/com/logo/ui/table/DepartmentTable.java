package tr.com.logo.ui.table;

import java.util.List;

import org.vaadin.viritin.fields.MTable;

import tr.com.logo.VaadinUI;
import tr.com.logo.domain.Department;

public class DepartmentTable extends MTable<Department> {

	public DepartmentTable() {
		withFullWidth();
		List<Department> departments = VaadinUI.get().getDepartmentService().getAllDepartments();
		if(departments != null) {
			setBeans(departments);
		}
	}

}
