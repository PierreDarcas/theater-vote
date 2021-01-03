package com.theater.app.converter;

import com.theater.app.dto.ViewerDTO;
import com.theater.app.model.Viewer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ViewerConverter {

    public ViewerDTO entityToDTO(Viewer viewer){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(viewer, ViewerDTO.class);
    }

    public List<ViewerDTO> entityToDTO(List<Viewer> viewers){
        return viewers.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public Viewer dtoToEntity(ViewerDTO viewerDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(viewerDTO, Viewer.class);
    }

    public List<Viewer> dtoToEntity(List<ViewerDTO> viewerDTOS){
        return viewerDTOS.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
