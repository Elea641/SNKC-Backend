package projet.wcs.starter.convertors;

import org.modelmapper.AbstractConverter;
import projet.wcs.starter.dao.User;

import java.util.List;
import java.util.stream.Collectors;

public class UsersListConvertor extends AbstractConverter<List<User>, List<Integer>> {

    @Override
    protected List<Integer> convert(List<User> users) {
        return users
                .stream()
                .map(User::getId)
                .collect(Collectors.toList());
    }
}
