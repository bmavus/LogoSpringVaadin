package tr.com.logo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.com.logo.domain.Classroom;
import tr.com.logo.domain.Student;
import tr.com.logo.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public void save(Student student) {
		studentRepository.saveAndFlush(student);
	}

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	public List<Student> getStudentsByClassroom(Classroom classroom) {
		return studentRepository.findByClassroom(classroom);
	}

}
