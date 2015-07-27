package tr.com.logo;


import java.util.List;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.Item;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import tr.com.logo.domain.Student;
import tr.com.logo.util.BeanItemContainerCreator;
import tr.com.logo.util.DemoDataGenerator;

@Title("Logo Demo")
@Widgetset("tr.com.logo.LogoVaadinWidgetSet")
@Theme("valo")
@SpringUI(path = "logo")
@SuppressWarnings({ "unchecked", "serial" })
public class VaadinUI extends UI {

	private Table studentTable;
	private TextField nameField;
	private TextField surnameField;
	private TextField idField;
	private TextField ageField;
	private Button editButton;
	private final Action DELETE_ACTION = new Action("Öğrenciyi Sil", FontAwesome.MINUS);
	private final Action[] ACTIONS = new Action[]{DELETE_ACTION};
	private BeanItemContainer<Student> bic;

	@Override
	protected void init(VaadinRequest request) {

		/* Create UI components */
		bic = BeanItemContainerCreator.createBeanItemContainer();
		Layout headerLayout = createButtonLayout();
		//		studentTable = createTableWithDefaultContainer();
		studentTable = createTableWithBeanItemContainer();
		/* Setting root content as rootLayout( used: VerticalLayout.class ) and
		 * add previously created components to rootLayout
		 *  */
		Layout rootLayout = createRootLayout(headerLayout, studentTable);
		setContent(rootLayout);
	}

	private VerticalLayout createRootLayout(Component... components) {
		VerticalLayout rootLayout = new VerticalLayout(components);
		rootLayout.setSpacing(true);
		rootLayout.setMargin(true);
		return rootLayout;
	}

	private HorizontalLayout createButtonLayout() {
		/* Initialize buttons */
		Button createButton = new Button("Yeni Öğrenci");
		editButton = new Button("Öğrenci Düzenle");
		editButton.setEnabled(false);

		/* Add button listeners */
		createButton.addClickListener(event -> {
			//			Notification.show("Yeni Öğrenci butonu tıklandı", Type.HUMANIZED_MESSAGE);
			FormLayout popupLayout = generatePopupLayout(null);
			Window popup = generatePopup(popupLayout, "Yeni Öğrenci");

			Button saveButton = new Button("Kaydet", FontAwesome.SAVE);
			saveButton.addClickListener(e -> {
				//				if(nameField.isValid()) {
				try {
					nameField.validate();
					Student student =
							new Student(
									nameField.getValue(),
									surnameField.getValue(),
									idField.getValue(),
									Integer.parseInt(ageField.getValue())
									);
					//					addOrEditTableItem(student);
					addTableItem(student);
					studentTable.select(student);
					studentTable.setCurrentPageFirstItemId(student);
					popup.close();
				} catch(InvalidValueException ex) {
					Notification.show("Adam gibi doldur", Type.ERROR_MESSAGE);
				}
				//				}
			});
			popupLayout.addComponent(saveButton);

			addWindow(popup);
		});
		editButton.addClickListener(this :: editStudent);

		/* Create and return layout with previously initialized buttons */
		HorizontalLayout buttonLayout = new HorizontalLayout(createButton, editButton);
		buttonLayout.setSpacing(true);

		return buttonLayout;

	}


	private Table createTableWithBeanItemContainer() {
		studentTable = new Table();
		studentTable.setSelectable(true);
		studentTable.setWidth(100, Unit.PERCENTAGE);
		studentTable.setPageLength(10);
		studentTable.setContainerDataSource(bic);

		studentTable.addValueChangeListener(event -> {
			if(event.getProperty().getValue() == null) {
				editButton.setEnabled(false);
				//				editButton.setVisible(visible);
			}
			else {
				editButton.setEnabled(true);
			}
		});
		studentTable.addActionHandler(new Handler() {

			@Override
			public void handleAction(Action action, Object sender, Object target) {
				if(action == DELETE_ACTION) {
					studentTable.removeItem(target);
				}
			}

			@Override
			public Action[] getActions(Object target, Object sender) {
				return ACTIONS;
			}
		});
		return studentTable;
	}
	private Table createTableWithDefaultContainer() {
		List<Student> studentList = DemoDataGenerator.createRandomStudents();

		studentTable = new Table();
		studentTable.setSelectable(true);
		studentTable.setWidth(100, Unit.PERCENTAGE);
		studentTable.setPageLength(10);

		studentTable.addContainerProperty("object", Student.class, null);
		studentTable.addContainerProperty("name", String.class, "");
		studentTable.addContainerProperty("surname", String.class, "");
		studentTable.addContainerProperty("studentId", String.class, "");
		studentTable.addContainerProperty("age", Label.class, null);

		studentTable.setVisibleColumns("name", "surname", "studentId", "age");

		studentTable.setColumnHeader("name", "Ad");
		studentTable.setColumnHeader("surname", "Soyad");
		studentTable.setColumnHeader("studentId", "Öğrenci No");
		studentTable.setColumnHeader("age", "Yaş");

		fillTable(studentList);

		studentTable.addValueChangeListener(event -> {
			if(event.getProperty().getValue() == null) {
				editButton.setEnabled(false);
				//				editButton.setVisible(visible);
			}
			else {
				editButton.setEnabled(true);
			}
		});
		studentTable.addActionHandler(new Handler() {

			@Override
			public void handleAction(Action action, Object sender, Object target) {
				if(action == DELETE_ACTION) {
					studentTable.removeItem(target);
				}
			}

			@Override
			public Action[] getActions(Object target, Object sender) {
				return ACTIONS;
			}
		});
		return studentTable;
	}

	/* Fills all table */
	private void fillTable(List<Student> studentList) {
		if(studentList != null) {
			for (Student student : studentList) {
				addOrEditTableItem(student);
			}
		}
	}

	/* Removes all items and refills the table */
	private void refillTable(List<Student> studentList) {
		studentTable.removeAllItems();
		fillTable(studentList);
	}

	/* Adds or edits single table item ( Table Demo with Default Container )*/
	private void addOrEditTableItem(Student student) {
		Object id = student.getStudentId();
		Item item = null;
		if(!studentTable.containsId(id)) {
			item = studentTable.addItem(id);
		}
		else {
			item = studentTable.getItem(id);
		}
		item.getItemProperty("object").setValue(student);
		item.getItemProperty("name").setValue(student.getName());
		item.getItemProperty("surname").setValue(student.getSurname());
		item.getItemProperty("studentId").setValue(id);
		Label ageLabel = new Label(student.getAge() + "");
		ageLabel.setStyleName(student.getAge() % 2 == 0 ? ValoTheme.LABEL_FAILURE : ValoTheme.LABEL_SUCCESS);
		item.getItemProperty("age").setValue(ageLabel);

	}

	/* Adds single table item ( Table Demo with Bean Item Container )*/
	private void addTableItem(Student student) {
		Item item = studentTable.addItem(student);
		studentTable.select(student);
	}

	private void editStudent(ClickEvent event) {
		//		Notification.show("Öğrenci Düzenle butonu tıklandı", Type.ERROR_MESSAGE);
		if(studentTable.getValue() != null) {
			/* Indexed Container*/
			//			Student student = (Student) studentTable.getContainerProperty(studentTable.getValue(), "object").getValue();
			/* Bean Item Container*/
			Student student = (Student) studentTable.getValue();
			FormLayout popupLayout = generatePopupLayout(student);
			Window popup = generatePopup(popupLayout, "Öğrenci Düzenle");
			Button saveButton = new Button("Kaydet", FontAwesome.SAVE);
			saveButton.addClickListener(e -> {
				if(nameField.isValid()) {
					student.setStudentId(idField.getValue());
					student.setName(nameField.getValue());
					student.setSurname(surnameField.getValue());
					student.setAge(Integer.parseInt(ageField.getValue()));
					//					addOrEditTableItem(student);
					bic.removeItem(student);
					bic.addItem(student);
					studentTable.setCurrentPageFirstItemId(student);
					studentTable.setCurrentPageFirstItemId(student);
					popup.close();
				}
			});
			popupLayout.addComponent(saveButton);
			addWindow(popup);
		}

	}

	private FormLayout generatePopupLayout(Student student) {
		nameField = new TextField("Ad");
		nameField.addValidator(new StringLengthValidator("İsim minimum 2 karakter maksimum 5 karakter olmalıdır",
				2,
				5,
				false
				));
		nameField.setImmediate(true);
		surnameField = new TextField("Soyad");
		//		surnameField.setRequired(true);
		idField = new TextField("Öğrenci No");
		ageField = new TextField("Yaş");

		FormLayout formLayout = new FormLayout(nameField, surnameField, idField, ageField);
		formLayout.setMargin(true);
		formLayout.setSpacing(true);

		if(student != null) {
			nameField.setValue(student.getName());
			surnameField.setValue(student.getSurname());
			idField.setValue(student.getStudentId());
			idField.setEnabled(false);
			ageField.setValue(student.getAge() + "");
		}


		return formLayout;
	}

	private Window generatePopup(FormLayout popupLayout, String caption) {
		Window popup = new Window(caption);
		popup.setModal(true);
		popup.center();
		popup.setWidth(500, Unit.PIXELS);
		popup.setResizable(false);
		popup.setDraggable(false);
		popup.setContent(popupLayout);
		return popup;
	}

}
