package reldb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import reldb.models.UserTeacher;

import java.util.List;

public interface UserTeacherRepository extends JpaRepository<UserTeacher, Integer> {

    List<UserTeacher> findAllByUser_Id(Integer userId);
}
