package projet.wcs.starter.convertors;

import org.modelmapper.AbstractConverter;
import projet.wcs.starter.dao.Room;

import java.util.List;
import java.util.stream.Collectors;

public class RoomsListConvertor extends AbstractConverter<List<Room>, List<Integer>> {

    @Override
    protected List<Integer> convert(List<Room> rooms) {
        return rooms
                .stream()
                .map(Room::getId)
                .collect(Collectors.toList());
    }
}
