package tr.com.logo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.com.logo.domain.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
