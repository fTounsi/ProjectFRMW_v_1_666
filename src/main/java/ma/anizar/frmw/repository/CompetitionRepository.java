package ma.anizar.frmw.repository;

import ma.anizar.frmw.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
}
