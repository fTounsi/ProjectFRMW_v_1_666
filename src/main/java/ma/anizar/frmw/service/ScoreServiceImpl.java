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
import org.springframework.beans.factory.config.YamlProcessor.MatchStatus;
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
    if (
      matchToSave.getEndTime() != null &&
      matchToSave.getStatus().equals(StatusMatch.EN_COURS) &&
      matchToSave.getEndTime().isBefore(LocalDateTime.now())
    ) {
      matchToSave.setStatus(StatusMatch.TERMINE);
    }
    matchRepository.save(matchToSave);
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
    matchToSave.setEndTime(LocalDateTime.now().plusSeconds(MATCH_DURATION));
    matchToSave.setStatus(StatusMatch.EN_COURS);
    matchToSave
      .getScores()
      .stream()
      .forEach(s -> {
        s.setRedScore(0);
        s.setBlueScore(0);
      });
    matchRepository.save(matchToSave);
    return matchToSave.toDTO();
  }

  @Override
  public MatchDTO restartMatchById(Long matchId) {
    Match matchToSave = matchRepository.findById(matchId).get();
    matchToSave.setStartTime(null);
    matchToSave.setEndTime(null);
    matchToSave.setStatus(StatusMatch.PROGRAMME);
    matchToSave
      .getScores()
      .stream()
      .forEach(s -> {
        s.setRedScore(0);
        s.setBlueScore(0);
      });
    matchRepository.save(matchToSave);
    return matchToSave.toDTO();
  }
}
