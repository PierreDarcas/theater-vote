package com.theater.app.controller;

import com.theater.app.converter.ViewerConverter;
import com.theater.app.dto.ViewerDTO;
import com.theater.app.model.Viewer;
import com.theater.app.repositories.ViewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.collections4.IterableUtils;

import java.util.List;
import java.util.NoSuchElementException;

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

}
