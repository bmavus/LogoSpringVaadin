package tr.com.logo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.com.logo.domain.Classroom;
import tr.com.logo.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	List<Student> findByClassroom(Classroom classroom);
	List<Student> findByClassroomAndNameLike(Classroom classroom, String name);
	//	@Query("select new A() * from sadfsda")
	//	List<Student> getStudentsByFullname(@Param);
}
