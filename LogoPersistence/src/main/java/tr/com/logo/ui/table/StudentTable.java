package tr.com.logo.ui.table;

import java.util.List;

import org.vaadin.viritin.fields.MTable;

import tr.com.logo.VaadinUI;
import tr.com.logo.domain.Student;

public class StudentTable extends MTable<Student> {

	public StudentTable() {
		withFullWidth().withProperties("name", "surname", "studentId", "classroom");
		withGeneratedColumn("classroom", entity -> {
			return entity.getClassroom() != null ?
					entity.getClassroom().getName() : "";
		});
		List<Student> students = VaadinUI.get().getStudentService().getAllStudents();
		if(students != null) {
			setBeans(students);
		}
	}

}
