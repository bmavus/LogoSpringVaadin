package tr.com.logo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.com.logo.domain.Classroom;
import tr.com.logo.domain.Department;
import tr.com.logo.repository.ClassroomRepository;

@Service
public class ClassroomService {

	@Autowired
	private ClassroomRepository classroomRepository;

	//	@Transactional
	public void save(Classroom classroom) {
		classroomRepository.saveAndFlush(classroom);
	}

	public List<Classroom> getAllClassrooms() {
		return classroomRepository.findAll();
	}

	public List<Classroom> getClassroomsByDepartment(Department department) {
		return classroomRepository.findByDepartment(department);
	}

	public List<Classroom> getClassroomsByName(String name) {
		return classroomRepository.findByName(name);
	}
}
