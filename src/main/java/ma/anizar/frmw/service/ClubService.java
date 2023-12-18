package ma.anizar.frmw.service;

import ma.anizar.frmw.model.Club;
import ma.anizar.frmw.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClubService {

    private final ClubRepository clubRepository;

    @Autowired
    public ClubService(ClubRepository clubRepository){this.clubRepository=clubRepository;}

    public String addClub(Club club) {

        try {
            clubRepository.save(club);
            return "Opération, effectuée avec succès.";
        }catch (Exception e){
            return "Erreur lors de l'ajout de la Competition";
        }
    }
}
