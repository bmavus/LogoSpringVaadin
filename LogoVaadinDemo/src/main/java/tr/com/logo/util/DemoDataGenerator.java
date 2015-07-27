package tr.com.logo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import tr.com.logo.domain.Student;

/**
 * This class is a utility class whose function is to
 * generate Student data set as java.util.List
 *
 * */
public class DemoDataGenerator {

	/* Number of students to be generated */
	private static final int NUM_STUDENTS = 100;

	public static List<Student> createRandomStudents() {
		List<Student> studentList = new ArrayList<Student>();
		for(int i = 1; i <= NUM_STUDENTS; i++) {
			studentList.add(new Student("Name" + i, "Surname" + i, UUID.randomUUID().toString(), i));
		}
		return studentList;
	}
}
