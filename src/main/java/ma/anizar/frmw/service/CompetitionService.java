package ma.anizar.frmw.service;

import ma.anizar.frmw.model.Competition;
import ma.anizar.frmw.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompetitionService {

    private final CompetitionRepository competitionRepository;

    @Autowired
    public CompetitionService(CompetitionRepository competitionRepository){this.competitionRepository=competitionRepository;}

    public String addCompetition(Competition competition) {

        try {
            competitionRepository.save(competition);
            return "Opération, effectuée avec succès.";
        }catch (Exception e){
            return "Erreur lors de l'ajout de la Competition";
        }
    }
}
