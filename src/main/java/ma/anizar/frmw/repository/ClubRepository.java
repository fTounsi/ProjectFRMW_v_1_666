package ma.anizar.frmw.repository;

import ma.anizar.frmw.model.Club;
import ma.anizar.frmw.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long> {

    Club findByUsersContains(Utilisateur user);

}
