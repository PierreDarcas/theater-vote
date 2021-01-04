package com.theater.app.converter;

import com.theater.app.dto.PlayDTO;
import com.theater.app.model.Play;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlayConverter {

    public PlayDTO entityToDTO(Play play){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(play, PlayDTO.class);
    }

    public List<PlayDTO> entityToDTO(List<Play> plays){
        return plays.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public Play dtoToEntity(PlayDTO playDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(playDTO, Play.class);
    }

    public List<Play> dtoToEntity(List<PlayDTO> playDTOS){
        return playDTOS.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

}
