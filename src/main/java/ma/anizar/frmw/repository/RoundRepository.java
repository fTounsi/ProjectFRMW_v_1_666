package ma.anizar.frmw.repository;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import ma.anizar.frmw.model.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RoundRepository extends JpaRepository<Round, Long> {
  @Transactional
  @Modifying
  @Query(
    "UPDATE Round r SET r.status = 2 WHERE r.status = 1 AND r.endTime < :currentTime"
  )
  void updateRoundStatusIfExpired(LocalDateTime currentTime);
}
