package projet.wcs.starter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import projet.wcs.starter.dao.Auction;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Integer> {
    @Query(value = "select * from auction as a where a.user_id = :id", nativeQuery = true)
    List<Auction> findByUserId(@Param("id") int id);

    @Query(value = "select * from auction as a where a.room_id = :id", nativeQuery = true)
    List<Auction> findByRoomId(@Param("id") int id);
}
