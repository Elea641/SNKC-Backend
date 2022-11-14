package projet.wcs.starter.convertors;

import org.modelmapper.AbstractConverter;
import projet.wcs.starter.dao.Picture;

import java.util.List;
import java.util.stream.Collectors;

public class PicturesListConvertor extends AbstractConverter<List<Picture>, List<Integer>> {

    @Override
    protected List<Integer> convert(List<Picture> pictures) {
        return pictures
                .stream()
                .map(Picture::getId)
                .collect(Collectors.toList());
    }
}
