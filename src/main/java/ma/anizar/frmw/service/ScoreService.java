package ma.anizar.frmw.service;

import ma.anizar.frmw.model.dto.MatchDTO;
import ma.anizar.frmw.model.dto.SanctionDTO;
import ma.anizar.frmw.model.dto.ScoreDTO;

public interface ScoreService {
  MatchDTO getMatchById(Long matchId);

  MatchDTO startNextRound(Long matchId);

  MatchDTO restartMatchById(Long matchId);

  boolean updateScore(ScoreDTO scoreDTO);

  boolean createSanction(SanctionDTO sanction);
}
