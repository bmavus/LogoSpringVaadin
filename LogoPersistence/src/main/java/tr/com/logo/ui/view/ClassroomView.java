package tr.com.logo.ui.view;

import org.vaadin.viritin.fields.MTable;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import tr.com.logo.VaadinUI;
import tr.com.logo.domain.Classroom;
import tr.com.logo.service.ClassroomService;
import tr.com.logo.ui.form.ClassroomForm;
import tr.com.logo.ui.table.ClassroomTable;

public class ClassroomView extends VerticalLayout implements View {

	private Button createButton, editButton;
	private MTable<Classroom> classroomTable;
	private ClassroomService classroomService;

	public ClassroomView() {
		classroomService = VaadinUI.get().getClassroomService();
		setSpacing(true);
		addComponent(createButtonLayout());
		addComponent(createTable());
	}

	private HorizontalLayout createButtonLayout() {
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setSpacing(true);
		//		buttonLayout.setMargin(new MarginInfo(false, false, false, true));

		createButton = new Button("Yeni Şube");
		createButton.addClickListener(event -> {
			ClassroomForm classroomForm = new ClassroomForm();
			classroomForm.setEntity(new Classroom());
			Window popup = classroomForm.openInModalPopup();
			classroomForm.setSavedHandler(entity -> {
				classroomService.save(entity);
				classroomTable.addItem(entity);
				popup.close();
				classroomTable.select(entity);
				classroomTable.setCurrentPageFirstItemId(entity);
			});
		});

		editButton = new Button("Bölüm Düzenle");
		editButton.addClickListener(event -> {
			ClassroomForm classroomForm = new ClassroomForm();
			classroomForm.setEntity(classroomTable.getValue());
			Window popup = classroomForm.openInModalPopup();
			classroomForm.setSavedHandler(entity -> {
				classroomService.save(entity);
				popup.close();
				classroomTable.sort();
				classroomTable.select(entity);
				classroomTable.setCurrentPageFirstItemId(entity);
			});
		});

		buttonLayout.addComponent(createButton);
		buttonLayout.addComponent(editButton);

		return buttonLayout;
	}

	private Table createTable() {
		classroomTable = new ClassroomTable();
		classroomTable.setSelectable(true);
		return classroomTable;
	}
	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Classroom View", Type.ERROR_MESSAGE);
	}

}
