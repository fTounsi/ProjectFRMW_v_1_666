package ma.anizar.frmw.service;

import ma.anizar.frmw.model.Match;
import ma.anizar.frmw.model.Score;

public interface ScoreService {

    Match getMatchById(Long matchId);
    
    Match startMatchById(Long matchId);

    Match endMatchById(Long matchId);

    boolean updateScore(Score scoreDTO);
}
