package projet.wcs.starter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import projet.wcs.starter.dao.Room;

import java.util.List;


@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query(value = "select * from room as r where r.owner_id = :id", nativeQuery = true)
    List<Room> findByOwnerId(@Param("id") int id);
}
