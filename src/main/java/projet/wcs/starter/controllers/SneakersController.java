package projet.wcs.starter.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.web.bind.annotation.*;
import projet.wcs.starter.dto.SneakersDto;
import projet.wcs.starter.repositories.SneakersRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class SneakersController {

    @Autowired
    private SneakersRepository repo;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/sneakers/all")
    public List<SneakersDto> allSneakers() {
        return repo.findAll().stream().map(
                sneakers -> modelMapper.map(sneakers, SneakersDto.class)
        ).collect(Collectors.toList());
    }

}

