package ma.anizar.frmw.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class MatchController {

  @GetMapping("/match/dashbord")
  public String matchDashbord(Model model, @RequestParam(name = "id") Long id) {
    return "dashbord";
  }

  @GetMapping("/match/arbitre")
  public String matchArbitre(
    Model model,
    @RequestParam(name = "matchId") Long matchId,
    @RequestParam(name = "arbitreName") String arbitreName
  ) {
    return "arbitre";
  }
}
