package tr.com.logo.domain;

public class Student {

	private String name;
	private String surname;
	private String studentId;
	private int age;
	public Student(String name, String surname, String studentId, int age) {
		super();
		this.name = name;
		this.surname = surname;
		this.studentId = studentId;
		this.age = age;
	}


	public Student(String name, String surname, String studentId) {
		super();
		this.name = name;
		this.surname = surname;
		this.studentId = studentId;
	}


	public Student() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}


}
