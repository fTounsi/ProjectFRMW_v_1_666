package ma.anizar.frmw.service;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import ma.anizar.frmw.repository.MatchRepository;
import ma.anizar.frmw.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

  private final RoundRepository roundRepository;
  private final MatchRepository matchRepository;

  @Autowired
  public ScheduledTask(
    RoundRepository roundRepository,
    MatchRepository matchRepository
  ) {
    this.roundRepository = roundRepository;
    this.matchRepository = matchRepository;
  }

  @Scheduled(fixedRate = 1000)
  public void performDatabaseTask() {
    roundRepository.updateRoundStatusIfExpired(LocalDateTime.now());
    matchRepository.updateMatchStatusIfAllRoundsTerminated();
  }
}
