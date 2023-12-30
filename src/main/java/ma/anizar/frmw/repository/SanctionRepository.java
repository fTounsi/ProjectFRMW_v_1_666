package ma.anizar.frmw.repository;

import ma.anizar.frmw.model.Sanction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SanctionRepository extends JpaRepository<Sanction, Long> {}
