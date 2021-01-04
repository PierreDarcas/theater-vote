package com.theater.app.bootstrap;

import com.theater.app.converter.PlayConverter;
import com.theater.app.converter.ViewerConverter;
import com.theater.app.dto.PlayDTO;
import com.theater.app.dto.ViewerDTO;
import com.theater.app.repositories.PlayRepository;
import com.theater.app.repositories.ViewerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class DataLoader implements CommandLineRunner{

    @Override
    public void run(String... args){
        loadViewers();
        loadPlays();
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

    private void loadPlays(){
        String[] title = {"Diner de cons","Cyrano de bergerac"};
        String[] synopsis = {
                "Pierre Brochant, célèbre éditeur parisien, organise chaque mercredi avec des amis un « dîner de cons » : chaque organisateur amène avec lui un « con », intarissable sur un sujet précis, qu'il a déniché au hasard. Ensuite, les organisateurs se moquent des « cons » toute la soirée sans que ces derniers s'en rendent compte. À l'issue du repas, on choisit le champion.",
                "Cyrano de Bergerac est amoureux de sa cousine Roxane. Mais, celle-ci lui avoue qu'elle aime Christian de Neuvillette, un nouveau cadet qu'elle lui fait promettre de protéger. Conscient de son manque d'esprit, Christian obtient par ailleurs de Cyrano, qu'il écrive pour lui ses lettres d'amour à la belle Roxane."
        };
        Date[] date = {Timestamp.valueOf("2020-07-31 20:00:00"), Timestamp.valueOf("2020-10-15 19:00:00") };
        List<PlayDTO> playDTOList = new ArrayList<>();
        for(int i = 0 ; i < 2 ; i++){
            PlayDTO playDTO = new PlayDTO(title[i],synopsis[i],date[i]);
            playDTOList.add(playDTO);
        }
        playRepository.saveAll(playConverter.dtoToEntity(playDTOList));
    }

    public DataLoader(ViewerRepository viewerRepository, ViewerConverter viewerConverter, PlayRepository playRepository, PlayConverter playConverter) {
        this.viewerRepository = viewerRepository;
        this.viewerConverter = viewerConverter;
        this.playRepository = playRepository;
        this.playConverter = playConverter;
    }

    private final ViewerRepository viewerRepository;
    private final ViewerConverter viewerConverter;
    private final PlayRepository playRepository;
    private final PlayConverter playConverter;
}
