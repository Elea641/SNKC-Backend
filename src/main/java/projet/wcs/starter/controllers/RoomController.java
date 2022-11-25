package projet.wcs.starter.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import projet.wcs.starter.dto.AuctionDto;
import projet.wcs.starter.dto.RoomDto;
import projet.wcs.starter.dao.Room;
import projet.wcs.starter.models.enums.ColorType;
import projet.wcs.starter.models.enums.StateOfWearType;
import projet.wcs.starter.repositories.AuctionRepository;
import projet.wcs.starter.repositories.RoomRepository;
import projet.wcs.starter.services.UserDetailsImpl;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class RoomController {

    @Autowired
    private RoomRepository roomRepo;

    @Autowired
    private AuctionRepository auctionRepo;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/rooms/all")
    public List<RoomDto> list() {
        return roomRepo.findAll().stream().map(
                rooms -> modelMapper.map(rooms, RoomDto.class)
        ).collect(Collectors.toList());
    }

    @GetMapping("/room/{id}")
    public RoomDto roomById(@PathVariable Integer id) {
        return modelMapper.map(roomRepo.findById(id).get(), RoomDto.class);
    }

    @GetMapping("/rooms")
    public List<RoomDto> roomByOwner() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return roomRepo.findByOwnerId(userDetails.getId())
                .stream()
                .map(
                rooms -> modelMapper.map(rooms, RoomDto.class)
        ).collect(Collectors.toList());
    }

    @GetMapping("/rooms/last")
    public List<RoomDto> roomsLast() {
        return roomRepo.findLastRooms()
                .stream()
                .map(room -> modelMapper.map(room, RoomDto.class)
                ).collect(Collectors.toList());
    }

    @GetMapping("/rooms/filter")
    public List<RoomDto> roomsFilter(@RequestParam(required = false) String brand, @RequestParam(required = false) String model, @RequestParam(required = false)
    Integer size, @RequestParam(required = false)StateOfWearType stateOfWear, @RequestParam(required = false) ColorType mainColor) {
        List<Room> rooms;
        boolean hasBrand = brand != null && brand.length() > 0;
        boolean hasModel = model != null && model.length() > 0;
        boolean hasSize = size != null && size > 0;
        boolean hasStateOfWear = stateOfWear != null;
        boolean hasMainColor = mainColor != null;
        if( hasSize && !hasBrand && !hasModel && !hasStateOfWear && !hasMainColor) {
            rooms = roomRepo.findBySneakersSize(size);
        } else if (!hasSize && hasBrand && !hasModel && !hasStateOfWear && !hasMainColor) {
            rooms = roomRepo.findBySneakersBrand(brand);
        } else if (!hasSize && !hasBrand && !hasModel && !hasStateOfWear && hasMainColor) {
            rooms = roomRepo.findBySneakersMainColor(mainColor);
        }  else if (!hasSize && !hasBrand && hasModel && !hasStateOfWear && !hasMainColor) {
            rooms = roomRepo.findBySneakersModel(model);
        }  else if (!hasSize && !hasBrand && !hasModel && hasStateOfWear && !hasMainColor) {
            rooms = roomRepo.findBySneakersStateOfWear(stateOfWear);
        } else if (!hasSize && hasBrand && !hasModel && !hasStateOfWear && hasMainColor) {
            rooms = roomRepo.findBySneakersMainColorAndSneakersBrand(mainColor, brand);
        } else if (hasSize && !hasBrand && !hasModel && !hasStateOfWear && hasMainColor) {
            rooms = roomRepo.findBySneakersMainColorAndSneakersSize(mainColor, size);
        } else if (!hasSize && !hasBrand && hasModel && !hasStateOfWear && hasMainColor) {
            rooms = roomRepo.findBySneakersMainColorAndSneakersModel(mainColor, model);
        } else if (!hasSize && !hasBrand && !hasModel && hasStateOfWear && hasMainColor) {
            rooms = roomRepo.findBySneakersMainColorAndSneakersStateOfWear(mainColor, stateOfWear);
        } else if (hasSize && hasBrand && !hasModel && !hasStateOfWear && !hasMainColor) {
            rooms = roomRepo.findBySneakersSizeAndSneakersBrand(size, brand);
        } else if (hasSize && !hasBrand && hasModel && !hasStateOfWear && !hasMainColor) {
            rooms = roomRepo.findBySneakersSizeAndSneakersModel(size, model);
        } else if (hasSize && !hasBrand && !hasModel && hasStateOfWear && !hasMainColor) {
            rooms = roomRepo.findBySneakersSizeAndSneakersStateOfWear(size, stateOfWear);
        } else if (!hasSize && hasBrand && !hasModel && hasStateOfWear && !hasMainColor) {
            rooms = roomRepo.findBySneakersBrandAndSneakersStateOfWear(brand, stateOfWear);
        } else if (!hasSize && hasBrand && hasModel && !hasStateOfWear && !hasMainColor) {
            rooms = roomRepo.findBySneakersBrandAndSneakersModel(brand, model);
        } else if (!hasSize && !hasBrand && hasModel && hasStateOfWear && !hasMainColor) {
            rooms = roomRepo.findBySneakersModelAndSneakersStateOfWear(model, stateOfWear);
        } else if (hasSize && hasBrand && hasModel && !hasStateOfWear && !hasMainColor) {
            rooms = roomRepo.findBySneakersSizeAndSneakersBrandAndSneakersModel(size, brand, model);
        } else if (hasSize && hasBrand && !hasModel && hasStateOfWear && !hasMainColor) {
            rooms = roomRepo.findBySneakersSizeAndSneakersBrandAndSneakersStateOfWear(size, brand, stateOfWear);
        } else if (hasSize && hasBrand && !hasModel && !hasStateOfWear && hasMainColor) {
            rooms = roomRepo.findBySneakersSizeAndSneakersBrandAndSneakersMainColor(size, brand, mainColor);
        } else if (hasSize && !hasBrand && hasModel && hasStateOfWear && !hasMainColor) {
            rooms = roomRepo.findBySneakersSizeAndSneakersModelAndSneakersStateOfWear(size, model, stateOfWear);
        } else if (hasSize && !hasBrand && hasModel && !hasStateOfWear && hasMainColor) {
            rooms = roomRepo.findBySneakersSizeAndSneakersModelAndSneakersMainColor(size, model, mainColor);
        } else if (!hasSize && hasBrand && hasModel && hasStateOfWear && !hasMainColor) {
            rooms = roomRepo.findBySneakersBrandAndSneakersModelAndSneakersStateOfWear(brand, model, stateOfWear);
        } else if (hasSize && !hasBrand && !hasModel && hasStateOfWear && hasMainColor) {
            rooms = roomRepo.findBySneakersSizeAndSneakersStateOfWearAndSneakersMainColor(size, stateOfWear, mainColor);
        } else if (!hasSize && hasBrand && hasModel && !hasStateOfWear && hasMainColor) {
            rooms = roomRepo.findBySneakersBrandAndSneakersModelAndSneakersMainColor(brand, model, mainColor);
        } else if (!hasSize && !hasBrand && hasModel && hasStateOfWear && hasMainColor) {
            rooms = roomRepo.findBySneakersModelAndSneakersStateOfWearAndSneakersMainColor(model, stateOfWear, mainColor);
        } else if (hasSize && hasBrand && hasModel && hasStateOfWear && !hasMainColor) {
            rooms = roomRepo.findBySneakersSizeAndSneakersBrandAndSneakersModelAndSneakersStateOfWear(size, brand, model, stateOfWear);
        } else if (hasSize && hasBrand && hasModel && !hasStateOfWear && hasMainColor) {
            rooms = roomRepo.findBySneakersSizeAndSneakersBrandAndSneakersModelAndSneakersMainColor(size, brand, model, mainColor);
        } else if (!hasSize && hasBrand && hasModel && hasStateOfWear && hasMainColor) {
            rooms = roomRepo.findBySneakersBrandAndSneakersModelAndSneakersStateOfWearAndSneakersMainColor(brand, model, stateOfWear, mainColor);
        } else {
            rooms = roomRepo.findBySneakersSizeAndSneakersBrandAndSneakersModelAndSneakersStateOfWearAndSneakersMainColor(size, brand, model, stateOfWear, mainColor);
        }
        return rooms
                .stream()
                .map(room -> modelMapper.map(room, RoomDto.class)
                ).toList();
    }

    @PostMapping("/rooms")
    public RoomDto createRoom(@RequestBody @Valid RoomDto room) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        room.setOwnerId(userDetails.getId());
        room.setStartDate(LocalDateTime.now());
        Date out = Date.from(room.getStartDate().atZone(ZoneId.systemDefault()).toInstant());
        Calendar c = Calendar.getInstance();
        c.setTime(out);
        c.add(Calendar.DATE, 7);
        room.setEndDate(c.getTime());
        Room savedRoom = roomRepo.save(modelMapper.map(room, Room.class));
        String roomUri = (URI.create("/room" + savedRoom.getId())).toString();
        room.setUri(roomUri);
        return modelMapper.map(savedRoom, RoomDto.class);
    }

    @PutMapping("/room/{id}")
    public RoomDto updateRoom(@RequestBody @Valid RoomDto room, @PathVariable Integer id) {
        RoomDto roomToUpdate = modelMapper.map(roomRepo.findById(id).get(), RoomDto.class);
        roomToUpdate.setInitialPrice(room.getInitialPrice());
        roomToUpdate.setSneakersId(room.getSneakersId());
        roomToUpdate.setWinnerId(room.getWinnerId());
        roomRepo.save(modelMapper.map(roomToUpdate, Room.class));
        return modelMapper.map(roomToUpdate, RoomDto.class);
    }

    @DeleteMapping("/room/{id}")
    public boolean deleteRoom(@PathVariable Integer id) {
        roomRepo.deleteById(id);
        return true;
    }

    @GetMapping("/room/{id}/auctions")
    public List<AuctionDto> auctionsByRoom(@PathVariable Integer id) {
        return auctionRepo.findByRoomId(id)
                .stream()
                .map(
                        auctions -> modelMapper.map(auctions, AuctionDto.class)
                ).collect(Collectors.toList());
    }
}
