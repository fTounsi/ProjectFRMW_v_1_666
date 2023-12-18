package ma.anizar.frmw.service;

import ma.anizar.frmw.model.Match;
import ma.anizar.frmw.model.Score;

public interface CachedDataService {
    
    Match getLiveMatchData(Long matchId);

    Match startMatch(Long matchId);
    Match endtMatch(Long matchId);

	boolean updateLiveScoreData(Score Score);

}
