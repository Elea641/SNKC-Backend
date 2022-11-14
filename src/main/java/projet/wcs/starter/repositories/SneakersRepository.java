package projet.wcs.starter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.wcs.starter.dao.Sneakers;

import java.util.List;
import java.util.Optional;

@Repository
public interface SneakersRepository extends JpaRepository<Sneakers, Integer> {

    Optional<List<Sneakers>> findAllByUserId(int userId);


}
