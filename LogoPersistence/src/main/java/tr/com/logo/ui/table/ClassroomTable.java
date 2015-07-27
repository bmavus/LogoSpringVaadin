package tr.com.logo.ui.table;

import java.util.List;

import org.vaadin.viritin.fields.MTable;

import tr.com.logo.VaadinUI;
import tr.com.logo.domain.Classroom;

public class ClassroomTable extends MTable<Classroom> {

	public ClassroomTable() {
		withFullWidth();
		List<Classroom> classrooms = VaadinUI.get().getClassroomService().getAllClassrooms();
		if(classrooms != null) {
			setBeans(classrooms);
		}
	}

}
