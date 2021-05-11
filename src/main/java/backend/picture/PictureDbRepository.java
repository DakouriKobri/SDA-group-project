package backend.picture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureDbRepository extends JpaRepository<Picture, Long> {
}
