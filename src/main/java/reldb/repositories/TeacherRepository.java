package reldb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import reldb.models.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
