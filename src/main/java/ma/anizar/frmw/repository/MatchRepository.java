package ma.anizar.frmw.repository;

import jakarta.transaction.Transactional;
import ma.anizar.frmw.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MatchRepository extends JpaRepository<Match, Long> {
  @Transactional
  @Modifying
  @Query(
    "UPDATE Match m SET m.status = 2 WHERE NOT EXISTS " +
    "(SELECT r FROM Round r WHERE r.match.matchId = m.matchId AND (r.status != 2 ))"
  )
  void updateMatchStatusIfAllRoundsTerminated();
}
