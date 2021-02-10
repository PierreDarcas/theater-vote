package com.theater.app.controller;

import com.theater.app.converter.ViewerConverter;
import com.theater.app.dto.ReviewDTO;
import com.theater.app.dto.ViewerDTO;
import com.theater.app.model.Review;
import com.theater.app.model.Viewer;
import com.theater.app.repositories.ViewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.collections4.IterableUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/viewer")
public class ViewerController {

    private final ViewerRepository viewerRepository;
    private final ViewerConverter viewerConverter;

    @Autowired
    public ViewerController(ViewerRepository viewerRepository, ViewerConverter viewerConverter) {
        this.viewerRepository = viewerRepository;
        this.viewerConverter = viewerConverter;
    }

    @GetMapping
    public List<ViewerDTO> findAll(){
        Iterable<Viewer> viewers = this.viewerRepository.findAll();
        if (IterableUtils.size(viewers) > 0){
            return this.viewerConverter.entityToDTO((IterableUtils.toList(viewers)));
        }else {
            throw new NoSuchElementException("Aucun spectateur");
        }
    }

    @GetMapping(path = "/{viewerId}")
    public ViewerDTO findViewerById(@PathVariable("viewerId") Long viewerId){
        Optional<Viewer> viewer = this.viewerRepository.findById(viewerId);
        if(viewer.isPresent()){
            return this.viewerConverter.entityToDTO(viewer.get());
        }else {
            throw new NoSuchElementException("Ce spectateur n'existe pas");
        }
    }

    @PostMapping(path ="/create")
    public ViewerDTO createViewer(@RequestBody ViewerDTO newViewerDTO){
        return viewerConverter.entityToDTO(viewerRepository.save(this.viewerConverter.dtoToEntity(newViewerDTO)));
    }

    @PutMapping(path ="/{viewerId}")
    public ViewerDTO updateViewer(@PathVariable("viewerId") Long viewerId, @RequestBody ViewerDTO newViewerDTO){
        Optional<Viewer> viewer = this.viewerRepository.findById(viewerId);
        if(!viewer.isPresent()){
            throw new NoSuchElementException("Critique inexistante");
        }else if(viewer.get().getId() != newViewerDTO.getIdViewer()){
            throw new IllegalArgumentException();
        }else{
            return  viewerConverter.entityToDTO(viewerRepository.save(this.viewerConverter.dtoToEntity(newViewerDTO)));
        }
    }

    @DeleteMapping(path = "/{viewerId}")
    public void deleteById(@PathVariable("viewerId") Long viewerId){
            this.viewerRepository.deleteById(viewerId);
    }
}
