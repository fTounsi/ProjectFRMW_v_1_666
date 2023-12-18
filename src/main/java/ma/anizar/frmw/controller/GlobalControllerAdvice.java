package ma.anizar.frmw.controller;

import ma.anizar.frmw.dto.UtilisateurDTO;
import ma.anizar.frmw.model.Utilisateur;
import ma.anizar.frmw.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {
    @Autowired
    private UtilisateurService utilisateurService;

    @ModelAttribute
    public void globalAttributes(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
            String username = auth.getName();

            // Fetch the user from the database
            Utilisateur user = utilisateurService.findByLogin(username).get();

            if (user != null) {
                UtilisateurDTO userDTO = new UtilisateurDTO();
                userDTO.setName(user.getFirstName());
                userDTO.setId(user.getId());
                userDTO.setRoles(user.getRoles().toString());
                userDTO.setAvatarUrl(user.getPhotoPath());
                model.addAttribute("user", userDTO);
            }
        }
    }

    private String getAvatarUrlForUser(String username) {
        // Implement this method to return the avatar URL for the user
        return "/images/default-avatar.png"; // Placeholder for example
    }
}