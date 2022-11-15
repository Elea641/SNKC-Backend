package projet.wcs.starter.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import projet.wcs.starter.dao.Auction;
import projet.wcs.starter.dto.AuctionDto;
import projet.wcs.starter.repositories.AuctionRepository;
import projet.wcs.starter.services.UserDetailsImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class AuctionController {

    @Autowired
    private AuctionRepository repo;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/auctions/all")
    public List<AuctionDto> list() {
        return repo.findAll().stream().map(
                auctions -> modelMapper.map(auctions, AuctionDto.class)
        ).collect(Collectors.toList());
    }

    @PostMapping("/auctions")
    public AuctionDto createAuction(@RequestBody @Valid AuctionDto auction) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        auction.setUserId(userDetails.getId());
        Auction savedAuction = repo.save(modelMapper.map(auction, Auction.class));
        return modelMapper.map(savedAuction, AuctionDto.class);
    }

    @GetMapping("/auctions")
    public List<AuctionDto> auctionByUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return repo.findByUserId(userDetails.getId())
                .stream()
                .map(
                        auctions -> modelMapper.map(auctions, AuctionDto.class)
                ).collect(Collectors.toList());
    }
}
