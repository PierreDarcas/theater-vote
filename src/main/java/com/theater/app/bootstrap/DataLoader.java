package com.theater.app.bootstrap;

import com.theater.app.converter.PlayConverter;
import com.theater.app.converter.ReviewConverter;
import com.theater.app.converter.ViewerConverter;
import com.theater.app.dto.PlayDTO;
import com.theater.app.dto.ReviewDTO;
import com.theater.app.dto.ViewerDTO;
import com.theater.app.repositories.PlayRepository;
import com.theater.app.repositories.ReviewRepository;
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
        loadReviews();
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

    public void loadReviews(){
        String[] title = {"Trés bonne expérience", "Un bon moment"};
        int[] note = {5,4};
        String[] comment = {
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc luctus lacinia tincidunt. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nulla ut convallis magna. Proin in suscipit velit.",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam semper massa vel elit porttitor scelerisque fermentum sit amet tortor. Aliquam erat volutpat. Praesent diam sapien, dignissim et auctor porttitor, lacinia ut."
        };
        Long[] viewerId = {1L,2L};
        Long[] playId = {2L,1L};
        List<ReviewDTO> reviewDTOList = new ArrayList<>();
        for(int i = 0 ; i < 2 ; i++){
            ReviewDTO reviewDTO = new ReviewDTO(title[i],note[i],comment[i],viewerId[i],playId[i]);
            reviewDTOList.add(reviewDTO);
        }
        reviewRepository.saveAll(reviewConverter.dtoToEntity(reviewDTOList));
    }

    public DataLoader(ViewerRepository viewerRepository, ViewerConverter viewerConverter, PlayRepository playRepository, PlayConverter playConverter, ReviewRepository reviewRepository, ReviewConverter reviewConverter) {
        this.viewerRepository = viewerRepository;
        this.viewerConverter = viewerConverter;
        this.playRepository = playRepository;
        this.playConverter = playConverter;
        this.reviewRepository = reviewRepository;
        this.reviewConverter = reviewConverter;
    }

    private final ViewerRepository viewerRepository;
    private final ViewerConverter viewerConverter;
    private final PlayRepository playRepository;
    private final PlayConverter playConverter;
    private final ReviewRepository reviewRepository;
    private final ReviewConverter reviewConverter;
}
