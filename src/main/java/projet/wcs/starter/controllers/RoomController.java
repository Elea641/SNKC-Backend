package projet.wcs.starter.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import projet.wcs.starter.dto.RoomDto;
import projet.wcs.starter.dao.Room;
import projet.wcs.starter.repositories.RoomRepository;
import projet.wcs.starter.services.UserDetailsImpl;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class RoomController {

    @Autowired
    private RoomRepository roomRepo;

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

    @PostMapping("/rooms")
    public RoomDto createRoom(@RequestBody @Valid RoomDto room) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        room.setOwnerId(userDetails.getId());
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
}
