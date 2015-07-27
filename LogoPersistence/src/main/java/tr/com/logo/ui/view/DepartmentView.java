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
import tr.com.logo.domain.Department;
import tr.com.logo.service.DepartmentService;
import tr.com.logo.ui.form.DepartmentForm;
import tr.com.logo.ui.table.DepartmentTable;

public class DepartmentView extends VerticalLayout implements View {

	private Button createButton, editButton;
	private MTable<Department> departmentTable;
	private DepartmentService departmentService;

	public DepartmentView() {
		departmentService = VaadinUI.get().getDepartmentService();
		setSpacing(true);
		addComponent(createButtonLayout());
		addComponent(createTable());
	}

	private HorizontalLayout createButtonLayout() {
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setSpacing(true);
		//		buttonLayout.setMargin(new MarginInfo(false, false, false, true));

		createButton = new Button("Yeni Bölüm");
		createButton.addClickListener(event -> {
			DepartmentForm departmentForm = new DepartmentForm();
			departmentForm.setEntity(new Department());
			Window popup = departmentForm.openInModalPopup();
			departmentForm.setSavedHandler(entity -> {
				departmentService.save(entity);
				departmentTable.addItem(entity);
				popup.close();
				departmentTable.select(entity);
				departmentTable.setCurrentPageFirstItemId(entity);
			});
		});

		editButton = new Button("Bölüm Düzenle");
		editButton.addClickListener(event -> {
			DepartmentForm departmentForm = new DepartmentForm();
			departmentForm.setEntity(departmentTable.getValue());
			Window popup = departmentForm.openInModalPopup();
			departmentForm.setSavedHandler(entity -> {
				departmentService.save(entity);
				popup.close();
				departmentTable.sort();
				departmentTable.select(entity);
				departmentTable.setCurrentPageFirstItemId(entity);
			});
		});

		buttonLayout.addComponent(createButton);
		buttonLayout.addComponent(editButton);

		return buttonLayout;
	}

	private Table createTable() {
		departmentTable = new DepartmentTable();
		departmentTable.setSelectable(true);
		return departmentTable;
	}
	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Department View", Type.ERROR_MESSAGE);
	}

}
