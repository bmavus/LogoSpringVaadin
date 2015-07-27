package tr.com.logo.ui.select;

import java.util.List;

import org.vaadin.viritin.fields.TypedSelect;

import com.vaadin.ui.ComboBox;

import tr.com.logo.VaadinUI;
import tr.com.logo.domain.Classroom;

@SuppressWarnings({ "unchecked", "serial" })
public class ClassroomComboBox extends TypedSelect<Classroom> {

	@Override
	public void attach() {
		super.attach();
	}
	public ClassroomComboBox(String caption) {
		withCaption(caption);
		withSelectType(ComboBox.class);
		setCaptionGenerator(option -> option.getName());
		List<Classroom> classrooms =
				VaadinUI.get().getClassroomService().getAllClassrooms();
		if(classrooms != null) {
			setOptions(classrooms);
		}
	}
}
