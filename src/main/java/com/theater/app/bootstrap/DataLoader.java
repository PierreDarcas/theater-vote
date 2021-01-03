package com.theater.app.bootstrap;

import com.theater.app.converter.ViewerConverter;
import com.theater.app.dto.ViewerDTO;
import com.theater.app.repositories.ViewerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class DataLoader implements CommandLineRunner{

    @Override
    public void run(String... args){
        loadViewers();
    }

    private void loadViewers(){
        String[] firstname = {"John","Paul"};
        String[] lastname = {"Lennon","McCartney"};
        String[] email = {"john.lennon@yelsub.com","paul.mccarteney@yelsub.com"};
        String[] username = {"jLennon","pMcCartney"};
        String[] password = {"StrawberryFields","Yesterday"};
        List<ViewerDTO> viewerDTOList = new ArrayList<>();
        for(int i = 0 ; i < 2 ; i++){
            ViewerDTO viewerDTO = new ViewerDTO(firstname[i],lastname[i],email[i],username[i],password[i]);
            viewerDTOList.add(viewerDTO);
        }
        viewerRepository.saveAll(viewerConverter.dtoToEntity(viewerDTOList));
    }

    public DataLoader(ViewerRepository viewerRepository, ViewerConverter viewerConverter) {
        this.viewerRepository = viewerRepository;
        this.viewerConverter = viewerConverter;
    }

    private final ViewerRepository viewerRepository;
    private final ViewerConverter viewerConverter;

}
