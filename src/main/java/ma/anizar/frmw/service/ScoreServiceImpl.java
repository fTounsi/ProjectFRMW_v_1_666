package ma.anizar.frmw.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;
import ma.anizar.frmw.model.Match;
import ma.anizar.frmw.model.Round;
import ma.anizar.frmw.model.Score;
import ma.anizar.frmw.model.dto.MatchDTO;
import ma.anizar.frmw.model.dto.ScoreDTO;
import ma.anizar.frmw.model.enums.StatusMatch;
import ma.anizar.frmw.model.enums.StatusRound;
import ma.anizar.frmw.repository.MatchRepository;
import ma.anizar.frmw.repository.RoundRepository;
import ma.anizar.frmw.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl implements ScoreService {

  @Autowired
  MatchRepository matchRepository;

  @Autowired
  RoundRepository roundRepository;

  @Autowired
  ScoreRepository scoreRepository;

  static final int ROUND_DURATION = 10;

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
  public MatchDTO startNextRound(Long matchId) {
    Match matchToSave = matchRepository.findById(matchId).get();

    Optional<Round> round = matchToSave
      .getRounds()
      .stream()
      .filter(r -> r.getStatus().equals(StatusRound.PROGRAMMED))
      .sorted(Comparator.comparingInt(Round::getOrderRound))
      .findFirst();
    round.ifPresent(r -> {
      r.setStatus(StatusRound.IN_PROGRESS);
      r.setStartTime(LocalDateTime.now());
      r.setEndTime(LocalDateTime.now().plusSeconds(ROUND_DURATION));
      r
        .getScores()
        .stream()
        .forEach(s -> {
          s.setRedScore(0);
          s.setBlueScore(0);
        });
      roundRepository.save(r);
      matchToSave.setStatus(StatusMatch.IN_PROGRESS);
      matchRepository.save(matchToSave);
    });

    return matchToSave.toDTO();
  }

  @Override
  public MatchDTO restartMatchById(Long matchId) {
    Match matchToSave = matchRepository.findById(matchId).get();
    matchToSave.setStatus(StatusMatch.PROGRAMMED);
    matchToSave
      .getRounds()
      .stream()
      .forEach(r -> {
        r.setStartTime(null);
        r.setEndTime(null);
        r.setStatus(StatusRound.PROGRAMMED);
        r
          .getScores()
          .stream()
          .forEach(s -> {
            s.setRedScore(0);
            s.setBlueScore(0);
          });
      });
    matchRepository.save(matchToSave);
    return matchToSave.toDTO();
  }
}
