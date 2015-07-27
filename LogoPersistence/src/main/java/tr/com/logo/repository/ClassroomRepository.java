package tr.com.logo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.com.logo.domain.Classroom;
import tr.com.logo.domain.Department;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
	List<Classroom> findByDepartment(Department department);
	List<Classroom> findByName(String name);
}
