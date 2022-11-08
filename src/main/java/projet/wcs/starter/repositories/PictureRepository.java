package projet.wcs.starter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.wcs.starter.entities.Picture;

public interface PictureRepository extends JpaRepository<Picture, Integer> {
}
