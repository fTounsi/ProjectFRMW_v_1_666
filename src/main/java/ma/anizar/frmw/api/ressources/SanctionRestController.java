package ma.anizar.frmw.api.ressources;

import ma.anizar.frmw.model.dto.MatchDTO;
import ma.anizar.frmw.model.dto.SanctionDTO;
import ma.anizar.frmw.model.dto.ScoreDTO;
import ma.anizar.frmw.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sanction")
public class SanctionRestController {

  // Injectez votre service ScoreService ici (assurez-vous de créer le service approprié)
  private final ScoreService scoreService;

  @Autowired
  public SanctionRestController(ScoreService scoreService) {
    this.scoreService = scoreService;
  }

  @PostMapping("/")
  public ResponseEntity<String> updateScore(@RequestBody SanctionDTO sanction) {
    boolean updated = scoreService.createSanction(sanction);

    // Vérifiez si la mise à jour a réussi
    if (updated) {
      return new ResponseEntity<>(
        "Score mis à jour avec succès.",
        HttpStatus.OK
      );
    } else {
      return new ResponseEntity<>(
        "Échec de la mise à jour du score.",
        HttpStatus.INTERNAL_SERVER_ERROR
      );
    }
  }
}
