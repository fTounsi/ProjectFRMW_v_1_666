package ma.anizar.frmw.api.ressources;

import ma.anizar.frmw.model.dto.MatchDTO;
import ma.anizar.frmw.model.dto.ScoreDTO;
import ma.anizar.frmw.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/match")
public class MatchRestController {

  // Injectez votre service ScoreService ici (assurez-vous de créer le service approprié)
  private final ScoreService scoreService;

  @Autowired
  public MatchRestController(ScoreService scoreService) {
    this.scoreService = scoreService;
  }

  // Méthode GET pour récupérer un match par son ID
  @GetMapping("/{matchId}")
  public ResponseEntity<MatchDTO> getMatchById(@PathVariable Long matchId) {
    // Utilisez le service pour récupérer les informations du match par son ID
    MatchDTO match = scoreService.getMatchById(matchId);

    // Vérifiez si le match a été trouvé
    if (match != null) {
      return new ResponseEntity<>(match, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("startNextRound/{matchId}")
  public ResponseEntity<MatchDTO> startNextRound(@PathVariable Long matchId) {
    MatchDTO match = scoreService.startNextRound(matchId);

    if (match != null) {
      return new ResponseEntity<>(match, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Méthode GET pour demarer un match par son ID
  @GetMapping("/restart/{matchId}")
  public ResponseEntity<MatchDTO> endMatchById(@PathVariable Long matchId) {
    System.out.println("API restartMatch " + matchId);

    // Utilisez le service pour récupérer les informations du match par son ID
    MatchDTO match = scoreService.restartMatchById(matchId);

    // Vérifiez si le match a été trouvé
    if (match != null) {
      return new ResponseEntity<>(match, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/updateScore")
  public ResponseEntity<String> updateScore(@RequestBody ScoreDTO score) {
    // Utilisez le service pour mettre à jour le score du match
    boolean updated = scoreService.updateScore(score);

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
