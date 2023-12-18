package ma.anizar.frmw.service;

import ma.anizar.frmw.model.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UtilisateurService {
    Long getUtilisateurCount();
    Page<Utilisateur> getAllUtilisateurs(int pageNo, int pageSize, String sortField, String sortOrder);
    void saveUtilisateur(Utilisateur utilisateur);
    Utilisateur getUtilisateur(Long id);
    void deleteUtilisateur(Long id);
    Page<Utilisateur> search(String firstName, String lastName, String login, int pageNo, int pageSize, String sortField, String sortOrder);
    Optional<Utilisateur> findByLogin(String login);
    Boolean existsByLogin(String login);
    String getLoggedInUserUsername();

    String addUtilisateur(Utilisateur utilisateur);
}