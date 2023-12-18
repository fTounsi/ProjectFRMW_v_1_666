package ma.anizar.frmw.service;

import ma.anizar.frmw.model.Utilisateur;
import ma.anizar.frmw.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurServiceImplementation implements UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public Long getUtilisateurCount() {
        return utilisateurRepository.count();
    }

    @Override
    public Page<Utilisateur> getAllUtilisateurs(int pageNo, int pageSize, String sortField, String sortOrder) {

        // if current order is "asc" then change it to "desc" and vice-versa
        Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return utilisateurRepository.findAll(pageable);
    }

    @Override
    public void saveUtilisateur(Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur getUtilisateur(Long id) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        return utilisateur.orElse(null);
    }

    @Override
    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

    @Override
    public Page<Utilisateur> search(String firstName, String lastName, String login, int pageNo, int pageSize, String sortField, String sortOrder) {

        // if current order is "asc" then change it to "desc" and vice-versa
        Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        if (firstName.length() > 0 && lastName.length() > 0 && login.length() > 0)
            return utilisateurRepository.findByFirstNameContainsIgnoreCaseAndLastNameContainsIgnoreCaseAndLoginContainsIgnoreCase(firstName, lastName, login, pageable);
        else if (firstName.length() > 0 && lastName.length() > 0)
            return utilisateurRepository.findByFirstNameContainsIgnoreCaseAndLastNameContainsIgnoreCase(firstName, lastName, pageable);
        else if (firstName.length() > 0 && login.length() > 0)
            return utilisateurRepository.findByFirstNameContainsIgnoreCaseAndLoginContainsIgnoreCase(firstName, login, pageable);
        else if (lastName.length() > 0 && login.length() > 0)
            return utilisateurRepository.findByLastNameContainsIgnoreCaseAndLoginContainsIgnoreCase(lastName, login, pageable);
        else if (firstName.length() > 0)
            return utilisateurRepository.findByFirstNameContainsIgnoreCase(firstName, pageable);
        else if (lastName.length() > 0)
            return utilisateurRepository.findByLastNameContainsIgnoreCase(lastName, pageable);
        else if (login.length() > 0)
            return utilisateurRepository.findByLoginContainsIgnoreCase(login, pageable);
        // dummy return, condition checked at controller
        return utilisateurRepository.findAll(pageable);
    }

    @Override
    public Optional<Utilisateur> findByLogin(String login) {
        return utilisateurRepository.findByLogin(login);
    }

    @Override
    public Boolean existsByLogin(String login) {
        return utilisateurRepository.existsByLogin(login);
    }

    @Override
    public String getLoggedInUserUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String loggedInUserLogin= userDetails.getUsername();
        Utilisateur loggedInUserDetails = utilisateurRepository.findByLogin(loggedInUserLogin).get();
        return loggedInUserDetails.getFirstName() + " " + loggedInUserDetails.getLastName();
    }

    @Override
    public String addUtilisateur(Utilisateur utilisateur) {
        if (utilisateurRepository.existsByLogin(utilisateur.getLogin())) {
            return "Error: Login " + utilisateur.getLogin() + " exist deja.";
        }
        utilisateurRepository.save(utilisateur);
        return "Opération, effectuée avec succès.";
    }
}