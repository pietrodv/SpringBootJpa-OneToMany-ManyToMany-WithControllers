package reldb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import reldb.models.Photo;
import java.util.List;
import java.util.Optional;


public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    List<Photo> findPhotoByUser_Id(Integer user_id);
    Optional<Photo> findByIdAndUser_Id(Integer photo_id, Integer user_id);
}