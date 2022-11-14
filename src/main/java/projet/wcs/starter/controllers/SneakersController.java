package projet.wcs.starter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import projet.wcs.starter.entities.Sneakers;
import projet.wcs.starter.repositories.SneakersRepository;
import projet.wcs.starter.repositories.UserRepository;
import projet.wcs.starter.services.UserDetailsImpl;

import java.util.List;

@RestController
@CrossOrigin
public class SneakersController {

    @Autowired
    private SneakersRepository repo;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/sneakers")
    public List<Sneakers> getAllSneakers() {
        return repo.findAll();
    }

    @PutMapping("/sneakers")
    public Sneakers createSneakers(@RequestBody Sneakers sneakers) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        sneakers.setUser(userRepository.findById(userDetails.getId()).get());
        return repo.save(sneakers);
    }


}
