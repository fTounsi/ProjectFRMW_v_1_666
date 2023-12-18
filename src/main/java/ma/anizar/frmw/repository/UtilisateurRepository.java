package ma.anizar.frmw.repository;

import ma.anizar.frmw.model.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {


    Optional<Utilisateur> findByLogin(String login);

    Optional<Utilisateur> findById(Long id);

    Utilisateur findUByLogin(String login);
    Boolean existsByLogin(String login);
    Page<Utilisateur> findByFirstNameContainsIgnoreCase(String firstName, Pageable pageable);
    Page<Utilisateur> findByLastNameContainsIgnoreCase(String lastName, Pageable pageable);
    Page<Utilisateur> findByLoginContainsIgnoreCase(String firstName, Pageable pageable);
    Page<Utilisateur> findByFirstNameContainsIgnoreCaseAndLastNameContainsIgnoreCase(String firstName, String lastName, Pageable pageable);
    Page<Utilisateur> findByFirstNameContainsIgnoreCaseAndLoginContainsIgnoreCase(String firstName, String lastName, Pageable pageable);
    Page<Utilisateur> findByLastNameContainsIgnoreCaseAndLoginContainsIgnoreCase(String lastName, String login, Pageable pageable);
    Page<Utilisateur> findByFirstNameContainsIgnoreCaseAndLastNameContainsIgnoreCaseAndLoginContainsIgnoreCase(String firstName, String lastName, String login, Pageable pageable);

}