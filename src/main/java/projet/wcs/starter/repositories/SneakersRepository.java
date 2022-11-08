package projet.wcs.starter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.wcs.starter.entities.Sneakers;

@Repository
public interface SneakersRepository extends JpaRepository<Sneakers, Integer> {
}
