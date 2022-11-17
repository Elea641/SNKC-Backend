package projet.wcs.starter.convertors;

import org.modelmapper.AbstractConverter;
import projet.wcs.starter.dao.Auction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuctionsListConvertor extends AbstractConverter<List<Auction>, List<Integer>> {

    @Override
    protected List<Integer> convert(List<Auction> auctions) {
        if (auctions != null) {
            return auctions
                    .stream()
                    .map(Auction::getId)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }


}
