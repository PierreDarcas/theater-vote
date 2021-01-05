package com.theater.app.controller;

import com.theater.app.converter.PlayConverter;
import com.theater.app.dto.PlayDTO;
import com.theater.app.dto.ReviewDTO;
import com.theater.app.dto.ViewerDTO;
import com.theater.app.model.Play;
import com.theater.app.model.Review;
import com.theater.app.model.Viewer;
import com.theater.app.repositories.PlayRepository;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/play")
public class PlayController {

    private final PlayConverter playConverter;
    private final PlayRepository playRepository;

    @Autowired
    public PlayController(PlayConverter playConverter, PlayRepository playRepository) {
        this.playConverter = playConverter;
        this.playRepository = playRepository;
    }

    @GetMapping
    public List<PlayDTO> findAll(){
        Iterable<Play> plays = this.playRepository.findAll();
        if(IterableUtils.size(plays) > 0){
            return this.playConverter.entityToDTO(IterableUtils.toList(plays));
        }else {
            throw new NoSuchElementException("Pas de pièces");
        }
    }

    @GetMapping(path = "/{playId}")
    public PlayDTO findPlayById(@PathVariable("playId") Long playId){
        Optional<Play> play = this.playRepository.findById(playId);
        if(play.isPresent()){
            return this.playConverter.entityToDTO(play.get());
        }else {
            throw new NoSuchElementException("Critique inexistante");
        }
    }

    @PostMapping(path ="/")
    public PlayDTO createPlay(@RequestBody PlayDTO newPlayDTO){
        return playConverter.entityToDTO(playRepository.save(this.playConverter.dtoToEntity(newPlayDTO)));
    }

    @PutMapping(path ="/{playId}")
    public PlayDTO updatePlay(@PathVariable("playId") Long playId, @RequestBody PlayDTO newPlayDTO){
        Optional<Play> play = this.playRepository.findById(playId);
        if(!play.isPresent()){
            throw new NoSuchElementException("Pièce inexistante");
        }else if(play.get().getId() != newPlayDTO.getIdPlay()){
            throw new IllegalArgumentException();
        }else{
            return playConverter.entityToDTO(playRepository.save(this.playConverter.dtoToEntity(newPlayDTO)));
        }
    }

    @DeleteMapping(path = "/{playId}")
    public void deletePlay(@PathVariable("playId") Long playId){
        this.playRepository.deleteById(playId);
    }

}
