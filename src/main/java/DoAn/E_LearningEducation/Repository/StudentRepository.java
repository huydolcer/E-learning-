package DoAn.E_LearningEducation.Repository;

import DoAn.E_LearningEducation.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    boolean existsByStudentId(String studentId);

}
