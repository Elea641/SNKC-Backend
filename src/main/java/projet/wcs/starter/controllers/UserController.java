package projet.wcs.starter.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import projet.wcs.starter.dao.User;
import projet.wcs.starter.dto.UserDto;
import projet.wcs.starter.models.ERole;
import projet.wcs.starter.models.Role;
import projet.wcs.starter.repositories.RoleRepository;
import projet.wcs.starter.repositories.UserRepository;
import projet.wcs.starter.services.UserDetailsImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository repo;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserDto> list() {
        return repo.findAll().stream().map(
                user -> modelMapper.map(user, UserDto.class)
        ).collect(Collectors.toList());
    }

    @GetMapping("/me")
    public UserDto me() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = repo.findById(userDetails.getId()).get();
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    @PutMapping("/me")
    public  UserDto updateMe(@RequestBody UserDto userDto) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = repo.findById(userDetails.getId()).get();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPicture(userDto.getPicture());
        user = repo.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @GetMapping("/{id}")
    public UserDto showUser(@PathVariable Integer id) {
        return modelMapper.map(repo.findById(id).get(), UserDto.class);
    }


}
