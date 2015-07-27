package tr.com.logo.util;

import com.vaadin.data.util.BeanItemContainer;

import tr.com.logo.domain.Student;

public class BeanItemContainerCreator {

	public static BeanItemContainer<Student> createBeanItemContainer() {
		BeanItemContainer<Student> bic =
				new BeanItemContainer<Student>(Student.class);
		bic.addAll(DemoDataGenerator.createRandomStudents());
		return bic;
	}
}
