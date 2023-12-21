package ma.anizar.frmw.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import ma.anizar.frmw.model.Club;
import ma.anizar.frmw.model.Member;
import ma.anizar.frmw.model.Region;
import ma.anizar.frmw.repository.ClubRepository;
import ma.anizar.frmw.repository.MemberRepository;
import ma.anizar.frmw.repository.RegionRepository;
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
    @RequestParam(name = "scoreId") Long scoreId
  ) {
    return "arbitre";
  }
}
