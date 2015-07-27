package tr.com.logo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.com.logo.domain.Department;
import tr.com.logo.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	public void save(Department department) {
		departmentRepository.saveAndFlush(department);
	}

	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}

}
