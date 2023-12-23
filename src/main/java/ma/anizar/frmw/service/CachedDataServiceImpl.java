package ma.anizar.frmw.service;

import ma.anizar.frmw.model.Competition;
import ma.anizar.frmw.model.Match;
import ma.anizar.frmw.model.Member;
import ma.anizar.frmw.model.Score;
import ma.anizar.frmw.model.enums.StatusMatch;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CachedDataServiceImpl implements CachedDataService {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(CachedDataServiceImpl.class);

    private Match matchData;

    public CachedDataServiceImpl(){
        log.info("Initialistion des matches ...........");
        matchData = Match.builder().match_id(Long.valueOf(123))
                .bluePlayer(Member.builder().clubName("Club Karaté Bleu").firstName("Khalid").lastName("Laarich").build())
                .redPlayer(Member.builder().clubName("Club Karaté Rouge").firstName("Fouad").lastName("Tounsi").build())
                .competition(Competition.builder().name("Wosho Championship regional Rabat").build())
                .startTime(null)
                .status(StatusMatch.PROGRAMME)
                .build();
        List<Score> scores = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            scores.add(Score.builder().arbitreName("Arbitre"+(i+1))
                    .blueScore(0)
                    .redScore(0)
                    .id(Long.valueOf(i))
                   // .match(Match.builder().match_id(Long.valueOf(123)).build())
                    .build());
        }
       // matchData.setScores(scores);

    }
    @Override
    public Match getLiveMatchData(Long matchId) {
        return matchData;
    }

    @Override
    public boolean updateLiveScoreData(Score score) {
        if(score != null) {
//            matchData.getScores().stream()
//                    .filter(item-> item.equals(score))
//                    .forEachOrdered(a ->{
//                        a.setRedScore(score.getRedScore());
//                        a.setBlueScore(score.getBlueScore());
//                        log.info("Matche Updated OK ");
//                    });
            return true;
        }else{
            return false;
        }
    }
    @Override
    public Match startMatch(Long matchId) {
        matchData = Match.builder().match_id(Long.valueOf(123))
                .bluePlayer(Member.builder().clubName("Club Karaté Bleu").firstName("Khalid").lastName("Laariche").build())
                .redPlayer(Member.builder().clubName("Club Karaté Rouge").firstName("Fouad").lastName("Tounsi").build())
                .competition(Competition.builder().name("Wosho Championship regional Rabat").build())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusMinutes(3))
                .status(StatusMatch.EN_COURS)
                .build();
        List<Score> scores = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            scores.add(Score.builder().arbitreName("Arbitre"+(i+1))
                    .blueScore(0)
                    .redScore(0)
                    .id(Long.valueOf(i))
                   // .match(Match.builder().match_id(Long.valueOf(123)).build())
                    .build());
        }
       // matchData.setScores(scores);
        return matchData;

    }
    @Override
    public Match endtMatch(Long matchId) {
        matchData.setEndTime(LocalDateTime.now());
        matchData.setStatus(StatusMatch.TERMINE);
        return matchData;
    }

}