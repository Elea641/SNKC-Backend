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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class SneakersController {

    @Autowired
    private SneakersRepository repo;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/sneakers/all")
    public List<SneakersDto> list() {
        return repo.findAll().stream().map(
                sneakers -> modelMapper.map(sneakers, SneakersDto.class)
        ).collect(Collectors.toList());
    }

    @GetMapping("/sneakers/{id}")
    public SneakersDto sneakersById(@PathVariable Integer id) {
        return modelMapper.map(repo.findById(id).get(), SneakersDto.class);
    }

    /**
     * Renvoie les sneakers de l'utilisateur connecté par défaut. Sinon celles de l'utilisateur passé en paramètre
     * @param userId Id de l'utilisateur.
     * @return Liste de sneakers Dto.
     */
    @GetMapping("/sneakers")
    public List<SneakersDto> sneakersByOwner(@RequestParam(required = false) Integer userId) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<List<Sneakers>>  sneakersDao = repo.findSneakerByUserId(userId != null ? userId : userDetails.getId());
        if (sneakersDao.isPresent() && sneakersDao.get().size() > 0){
            return sneakersDao.stream()
                    .map(sneakers -> modelMapper.map(sneakers, SneakersDto.class)
                    ).collect(Collectors.toList());
        }
        return new ArrayList<SneakersDto>();
    }

    @PostMapping("/sneakers")
    public SneakersDto createSneakers(@RequestBody @Valid SneakersDto sneaker) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        sneaker.setUserId(userDetails.getId());
        sneaker.setMainColor(String.valueOf(ColorType.BLACK));
        sneaker.setStateOfWear(String.valueOf(StateOfWearType.NEUF));
        Sneakers savedSneaker = repo.save(modelMapper.map(sneaker, Sneakers.class));
        String sneakerUri = (URI.create("/sneakers" + savedSneaker.getId())).toString();
        sneaker.setUri(sneakerUri);
        return modelMapper.map(savedSneaker, SneakersDto.class);
    }

    @PutMapping("/sneakers/{id}")
    public SneakersDto updateSneakers(@RequestBody @Valid SneakersDto sneakers, @PathVariable Integer id) {
        SneakersDto sneakersToUpdate = modelMapper.map(repo.findById(id).get(), SneakersDto.class);
        sneakersToUpdate.setBrand(sneakers.getBrand());
        sneakersToUpdate.setModel(sneakers.getModel());
        sneakersToUpdate.setSize(sneakers.getSize());
        sneakersToUpdate.setStateOfWear(sneakers.getStateOfWear());
        sneakersToUpdate.setMainColor(sneakers.getMainColor());
        sneakersToUpdate.setPicturesId(sneakers.getPicturesId());
        repo.save(modelMapper.map(sneakersToUpdate, Sneakers.class));
        return modelMapper.map(sneakersToUpdate, SneakersDto.class);
    }

    @DeleteMapping("/sneakers/{id}")
    public boolean deleteSneakers(@PathVariable Integer id) {
        repo.deleteById(id);
        return true;
    }
}


