package projet.wcs.starter.configurations;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import projet.wcs.starter.convertors.*;
import projet.wcs.starter.dto.PictureDto;
import projet.wcs.starter.dto.RoomDto;
import projet.wcs.starter.dto.SneakersDto;
import projet.wcs.starter.dto.UserDto;
import projet.wcs.starter.dao.Picture;
import projet.wcs.starter.dao.Room;
import projet.wcs.starter.dao.Sneakers;
import projet.wcs.starter.dao.User;

@Configuration
public class Mapper {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        TypeMap<Room, RoomDto> propertyMapperRoom = modelMapper.createTypeMap(Room.class, RoomDto.class);
        propertyMapperRoom.addMappings(
                mapper -> {
                        mapper.map(src -> src.getOwner().getId(), RoomDto::setOwnerId);
                        mapper.map(src -> src.getWinner().getId(), RoomDto::setWinnerId);
                        mapper.map(src -> src.getSneakers().getId(), RoomDto::setSneakersId);
                        mapper.using(new AuctionsListConvertor()).map(Room::getAuctions, RoomDto::setAuctionsId);
                }
        );

        TypeMap<User, UserDto> propertyMapperUser =  modelMapper.createTypeMap(User.class, UserDto.class);
        propertyMapperUser.addMappings(
                mapper -> {
                    mapper.using(new SneakersListConvertor()).map(User::getSneakers, UserDto::setSneakersId);
                    mapper.using(new RoomsListConvertor()).map(User::getUserRooms, UserDto::setUserRoomsId);;
                    mapper.using(new RoomsListConvertor()).map(User::getAttendingRooms, UserDto::setAttendingRoomsId);
                    mapper.using(new AuctionsListConvertor()).map(User::getAuctions, UserDto::setAuctionsId);
                }
        );

        TypeMap<Sneakers, SneakersDto> propertyMapperSneakers = modelMapper.createTypeMap(Sneakers.class, SneakersDto.class);
        propertyMapperSneakers.addMappings(
                mapper -> {
                    mapper.map(src -> src.getUser().getId(), SneakersDto::setUserId);
                    mapper.using(new PicturesListConvertor()).map(Sneakers::getPictures, SneakersDto::setPicturesId);;
                }
        );

        TypeMap<Picture, PictureDto> propertyMapperPicture = modelMapper.createTypeMap(Picture.class, PictureDto.class);
        propertyMapperPicture.addMappings(
                mapper -> {
                    mapper.using(new SneakersListConvertor()).map(Picture::getSneakers, PictureDto::setSneakersId);
                }
        );
    return modelMapper;
    }
}
