package projet.wcs.starter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import projet.wcs.starter.dao.Room;
import projet.wcs.starter.models.enums.ColorType;
import projet.wcs.starter.models.enums.StateOfWearType;

import java.util.Date;
import java.util.List;


@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query(value = "select * from room as r where r.owner_id = :id", nativeQuery = true)
    List<Room> findByOwnerId(@Param("id") int id);

    @Query(value = "select * from room order by id desc limit 10", nativeQuery = true)
    List<Room> findLastRooms();

    @Query(value = "select * from room as r where r.end_date > :now and r.owner_id = :id", nativeQuery = true)
    List<Room> findOpenRooms(@Param("now") Date now, @Param("id") int id);

    @Query(value = "select * from room as r where r.end_date <= :now and r.owner_id = :id", nativeQuery = true)
    List<Room> findClosedRooms(@Param("now") Date now, @Param("id") int id);

    @Query(value = "select r.end_date, r.start_date, r.sneakers_id, r.initial_price, r.winner_id, r.id, r.owner_id " +
            "from room as r JOIN auction as a on r.id = a.room_id where a.user_id = :id and r.end_date > now() " +
            "group by r.id", nativeQuery = true)
    List<Room> findAttendingOpenRooms(@Param("id") int id);

    @Query(value = "select brand, model, size, state_of_wear, main_color from room inner join sneakers on sneakers.id=room.sneakers_id;", nativeQuery = true)
    List<Room> findRoomFilter();

    List<Room> findBySneakersSize(int size);
    List<Room> findBySneakersBrand(String brand);
    List<Room> findBySneakersMainColor(ColorType mainColor);
    List<Room> findBySneakersModel(String model);
    List<Room> findBySneakersStateOfWear(StateOfWearType stateOfWear);
    List<Room> findBySneakersMainColorAndSneakersBrand(ColorType mainColor, String brand);
    List<Room> findBySneakersMainColorAndSneakersModel(ColorType mainColor, String model);
    List<Room> findBySneakersMainColorAndSneakersStateOfWear(ColorType mainColor, StateOfWearType stateOfWear);
    List<Room> findBySneakersMainColorAndSneakersSize(ColorType mainColor, int size);
    List<Room> findBySneakersSizeAndSneakersBrand(int size, String brand);
    List<Room> findBySneakersSizeAndSneakersModel(int size, String model);
    List<Room> findBySneakersSizeAndSneakersStateOfWear(int size, StateOfWearType stateOfWear);
    List<Room> findBySneakersBrandAndSneakersStateOfWear(String brand, StateOfWearType stateOfWear);
    List<Room> findBySneakersBrandAndSneakersModel(String brand, String model);
    List<Room> findBySneakersModelAndSneakersStateOfWear(String model, StateOfWearType stateOfWear);
    List<Room> findBySneakersSizeAndSneakersBrandAndSneakersModel(int size, String brand, String model);
    List<Room> findBySneakersSizeAndSneakersBrandAndSneakersStateOfWear(int size, String brand, StateOfWearType stateOfWear);
    List<Room> findBySneakersSizeAndSneakersBrandAndSneakersMainColor(int size, String brand, ColorType mainColor);
    List<Room> findBySneakersSizeAndSneakersModelAndSneakersStateOfWear(int size, String model, StateOfWearType stateOfWear);
    List<Room> findBySneakersSizeAndSneakersModelAndSneakersMainColor(int size, String model, ColorType mainColor);
    List<Room> findBySneakersBrandAndSneakersStateOfWearAndSneakersMainColor(String brand, StateOfWearType stateOfWear, ColorType mainColor);
    List<Room> findBySneakersBrandAndSneakersModelAndSneakersStateOfWear(String brand, String model, StateOfWearType stateOfWear);
    List<Room> findBySneakersBrandAndSneakersModelAndSneakersMainColor(String brand, String model, ColorType mainColor);
    List<Room> findBySneakersSizeAndSneakersStateOfWearAndSneakersMainColor(int size, StateOfWearType stateOfWear, ColorType mainColor);
    List<Room> findBySneakersModelAndSneakersStateOfWearAndSneakersMainColor(String model,StateOfWearType stateOfWear, ColorType mainColor);
    List<Room> findBySneakersSizeAndSneakersBrandAndSneakersModelAndSneakersStateOfWear(int size, String brand, String model, StateOfWearType stateOfWear);
    List<Room> findBySneakersSizeAndSneakersBrandAndSneakersModelAndSneakersMainColor(int size, String brand, String model, ColorType mainColor);
    List<Room> findBySneakersBrandAndSneakersModelAndSneakersStateOfWearAndSneakersMainColor(String brand, String model, StateOfWearType stateOfWear, ColorType mainColor);
    List<Room> findBySneakersModelAndSneakersSizeAndSneakersStateOfWearAndSneakersMainColor(String model, int size, StateOfWearType stateOfWear, ColorType mainColor);
    List<Room> findBySneakersSizeAndSneakersBrandAndSneakersStateOfWearAndSneakersMainColor(int size, String brand, StateOfWearType stateOfWear, ColorType mainColor);
    List<Room> findBySneakersSizeAndSneakersBrandAndSneakersModelAndSneakersStateOfWearAndSneakersMainColor(int size, String brand, String model, StateOfWearType stateOfWear, ColorType mainColor);
}
