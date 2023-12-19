package ma.anizar.frmw.service;

import ma.anizar.frmw.model.dto.MatchDTO;
import ma.anizar.frmw.model.dto.ScoreDTO;

public interface ScoreService {
  MatchDTO getMatchById(Long matchId);

  MatchDTO startMatchById(Long matchId);

  MatchDTO endMatchById(Long matchId);

  boolean updateScore(ScoreDTO scoreDTO);
}
