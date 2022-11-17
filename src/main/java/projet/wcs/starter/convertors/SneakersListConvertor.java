package projet.wcs.starter.convertors;

import org.modelmapper.AbstractConverter;
import projet.wcs.starter.dao.Room;
import projet.wcs.starter.dao.Sneakers;

import java.util.List;
import java.util.stream.Collectors;

public class SneakersListConvertor extends AbstractConverter<List<Sneakers>, List<Integer>> {

    @Override
    protected List<Integer> convert(List<Sneakers> sneakers) {
        return sneakers
                .stream()
                .map(Sneakers::getId)
                .collect(Collectors.toList());
    }
}
