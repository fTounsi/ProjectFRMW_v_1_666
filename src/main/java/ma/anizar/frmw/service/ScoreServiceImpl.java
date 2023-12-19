package ma.anizar.frmw.service;

import java.time.LocalDateTime;
import ma.anizar.frmw.model.Match;
import ma.anizar.frmw.model.Score;
import ma.anizar.frmw.model.StatusMatch;
import ma.anizar.frmw.model.dto.MatchDTO;
import ma.anizar.frmw.model.dto.ScoreDTO;
import ma.anizar.frmw.repository.MatchRepository;
import ma.anizar.frmw.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl implements ScoreService {

  @Autowired
  CachedDataService cachedDataService;

  @Autowired
  MatchRepository matchRepository;

  @Autowired
  ScoreRepository scoreRepository;

  static final int MATCH_DURATION = 10;

  @Override
  public MatchDTO getMatchById(Long matchId) {
    Match matchToSave = matchRepository.findById(matchId).get();

    return matchToSave.toDTO();
  }

  @Override
  public boolean updateScore(ScoreDTO score) {
    Score scoreToSave = scoreRepository.findById(score.getId()).get();
    scoreToSave.setRedScore(score.getRedScore());
    scoreToSave.setBlueScore(score.getBlueScore());
    scoreRepository.save(scoreToSave);
    return true;
  }

  @Override
  public MatchDTO startMatchById(Long matchId) {
    Match matchToSave = matchRepository.findById(matchId).get();
    matchToSave.setStartTime(LocalDateTime.now());
    matchToSave.setEndTime(LocalDateTime.now().plusMinutes(MATCH_DURATION));
    matchToSave.setStatus(StatusMatch.EN_COURS);

    return matchToSave.toDTO();
  }

  @Override
  public MatchDTO endMatchById(Long matchId) {
    Match matchToSave = matchRepository.findById(matchId).get();
    matchToSave.setEndTime(LocalDateTime.now());
    matchToSave.setStatus(StatusMatch.TERMINE);

    return matchToSave.toDTO();
  }
}
