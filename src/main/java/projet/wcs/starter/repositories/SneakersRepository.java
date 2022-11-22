package projet.wcs.starter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import projet.wcs.starter.dao.Sneakers;

import java.util.List;

@Repository
public interface SneakersRepository extends JpaRepository<Sneakers, Integer> {

    @Query(value = "select * from sneakers as s where s.user_id = :id", nativeQuery = true)
    List<Sneakers> findSneakerByUserId(@Param("id") int id);

    @Query(value = "select * from sneakers order by id desc limit 10", nativeQuery = true)
    List<Sneakers> findLastSneakers();
}
