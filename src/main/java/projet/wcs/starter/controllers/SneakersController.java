package projet.wcs.starter.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import projet.wcs.starter.dao.Sneakers;
import projet.wcs.starter.dto.SneakersDto;
import projet.wcs.starter.models.enums.ColorType;
import projet.wcs.starter.models.enums.StateOfWearType;
import projet.wcs.starter.repositories.SneakersRepository;
import projet.wcs.starter.services.UserDetailsImpl;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class SneakersController {

    @Autowired
    private SneakersRepository repoSneakers;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/sneakers/all")
    public List<SneakersDto> list() {
        return repoSneakers.findAll().stream().map(
                sneakers -> modelMapper.map(sneakers, SneakersDto.class)
        ).collect(Collectors.toList());
    }

    @GetMapping("/sneakers/{id}")
    public SneakersDto sneakersById(@PathVariable Integer id) {
        return modelMapper.map(repoSneakers.findById(id).get(), SneakersDto.class);
    }

    /**
     * Renvoie les sneakers de l'utilisateur connecté par défaut. Sinon celles de l'utilisateur passé en paramètre
     * @param userId Id de l'utilisateur.
     * @return Liste de sneakers Dto.
     */
    @GetMapping("/sneakers")
    public List<SneakersDto> sneakersByOwner(@RequestParam(required = false) Integer userId) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<List<Sneakers>>  sneakersDao = repoSneakers.findSneakerByUserId(userId != null ? userId : userDetails.getId());
        if (sneakersDao.isPresent() && sneakersDao.get().size() > 0){
            return sneakersDao.stream()
                    .map(sneakers -> modelMapper.map(sneakers, SneakersDto.class)
                    ).collect(Collectors.toList());
        }
        return new ArrayList<SneakersDto>();
    }

    @PostMapping("/sneakers")
    public SneakersDto createSneakers(@RequestBody @Valid SneakersDto sneakersDto) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        sneakersDto.setUserId(userDetails.getId());
        sneakersDto.setMainColor(String.valueOf(ColorType.BLACK));
        sneakersDto.setStateOfWear(String.valueOf(StateOfWearType.NEUF));
        Sneakers sneakers = modelMapper.map(sneakersDto, Sneakers.class);
        sneakers.setPictureString(sneakersDto.getPicture());
        sneakers = repoSneakers.save(sneakers);
        String sneakerUri = (URI.create("/sneakers" + sneakers.getId())).toString();
        sneakersDto = modelMapper.map(sneakers, SneakersDto.class);
        sneakersDto.setUri(sneakerUri);
        return sneakersDto;
    }

    @PutMapping("/sneakers/{id}")
    public SneakersDto updateSneakers(@RequestBody @Valid SneakersDto sneakersDto, @PathVariable Integer id) {
        SneakersDto sneakersToUpdate = modelMapper.map(repoSneakers.findById(id).get(), SneakersDto.class);
        sneakersToUpdate.setBrand(sneakersDto.getBrand());
        sneakersToUpdate.setModel(sneakersDto.getModel());
        sneakersToUpdate.setSize(sneakersDto.getSize());
        sneakersToUpdate.setStateOfWear(sneakersDto.getStateOfWear());
        sneakersToUpdate.setMainColor(sneakersDto.getMainColor());
        sneakersToUpdate.setPicture(Arrays.toString(sneakersDto.getPicture().getBytes()));
        repoSneakers.save(modelMapper.map(sneakersToUpdate, Sneakers.class));
        return modelMapper.map(sneakersToUpdate, SneakersDto.class);
    }

    @DeleteMapping("/sneakers/{id}")
    public boolean deleteSneakers(@PathVariable Integer id) {
        repoSneakers.deleteById(id);
        return true;
    }
}


