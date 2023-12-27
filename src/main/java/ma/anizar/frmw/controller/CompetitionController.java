package ma.anizar.frmw.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import lombok.AllArgsConstructor;
import ma.anizar.frmw.model.*;
import ma.anizar.frmw.model.enums.StatusMatch;
import ma.anizar.frmw.repository.ClubRepository;
import ma.anizar.frmw.repository.CompetitionRepository;
import ma.anizar.frmw.repository.MatchRepository;
import ma.anizar.frmw.service.CompetitionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class CompetitionController {

  private CompetitionRepository competitionRepository;
  private ClubRepository clubRepository;
  private final CompetitionService competitionService;
  private MatchRepository matchRepository;

  @GetMapping("/competition")
  public String competition(Model model) {
    //Competition
    List<Competition> competitionList = competitionRepository.findAll();
    model.addAttribute("listCompetitions", competitionList);
    return "competition";
  }

  @GetMapping("/formCompetition")
  public String formCompetition(Model model) {
    model.addAttribute("comp", new Competition());
    return "formCompetition";
  }

  @PostMapping("/saveCompetition")
  public String saveCompetition(
    @ModelAttribute Competition competition,
    Model model,
    RedirectAttributes redirectAttributes
  ) {
    if (competition.getStatus() == null) {
      competition.setStatus("EN COURS");
    }
    String response = competitionService.addCompetition(competition);
    if (response.startsWith("Error")) {
      redirectAttributes.addFlashAttribute("errorMessage", response);
      model.addAttribute("errorMessage", response);
      System.out.println("############### ############### " + response);
      return "formCompetition";
    }
    model.addAttribute("successMessage", response);
    redirectAttributes.addFlashAttribute("successMessage", response);
    return "redirect:/competition";
  }

  @GetMapping("/editCompetition")
  public String editCompetition(
    Model model,
    @RequestParam(name = "id") Long id
  ) {
    Competition competition = competitionRepository.findById(id).get();
    model.addAttribute("comp", competition);
    return "editCompetition";
  }

  @GetMapping("/detailCompetition")
  public String detailCompetition(
    Model model,
    @RequestParam(name = "id") Long id
  ) {
    //Club
    List<Club> clubList = clubRepository.findAll();
    model.addAttribute("listClubs", clubList);
    //List<Member> memberList = memberRepository.findAll();

    Competition competition = competitionRepository.findById(id).get();
    model.addAttribute("listCandidats", competition.getParticipatedMembers());
    //model.addAttribute("selectedListClubs",competition.getParticipatedClubs());
    model.addAttribute("comp", competition);
    return "detailCompetition";
  }

  @PostMapping("/addClubsToComp/{id}")
  public String handleMultiSelect(
    @PathVariable Long id,
    @RequestBody Map<String, Object> payload,
    Model model
  ) {
    List<String> selectedValues = (List<String>) payload.get("selectedValues");
    System.out.println("################  " + selectedValues.stream().toList());
    Competition competition = competitionRepository.findById(id).get();
    List<Club> participatedClubs = new ArrayList<>();
    for (String str : selectedValues) {
      Club c = clubRepository.findById(Long.parseLong(str)).get();
      participatedClubs.add(c);
    }
    competition.getParticipatedClubs().addAll(participatedClubs);
    competitionRepository.save(competition);
    model.addAttribute("comp", competition);
    System.out.println(
      "@@@@@@@@@@@@@@@@@@@@@@@@@@@^^^^^^^^^  " +
      competition.getParticipatedClubs().size()
    );
    //return "redirect:/detailCompetition?id="+competition.getId();
    return "redirect:/detailCompetition?id=" + competition.getId();
  }

  @PostMapping("/generateMatches")
  public String submitItems(@RequestParam("idcomp") Long id, Model model)
    throws JsonProcessingException {
    Competition competition = competitionRepository.findById(id).get();
    List<Member> participatedMembers = new ArrayList<>();
    participatedMembers = competition.getParticipatedMembers();
    System.out.println(
      "PARTICIPANTS LIST SIZE: @######## =======>>>>>>>>    " +
      participatedMembers.size()
    );

    String[] participantsJckson = new String[participatedMembers.size()];
    for (int i = 0; i < participatedMembers.size(); i++) {
      participantsJckson[i] =
        participatedMembers.get(i).getFirstName() +
        " " +
        participatedMembers.get(i).getLastName();
    }

    System.out.println("List: " + Arrays.toString(participantsJckson));
    ObjectMapper mapper = new ObjectMapper();
    String participantsJson = mapper.writeValueAsString(participantsJckson); // Convert the array to a JSON string
    model.addAttribute("participantsJckson", participantsJson); // Add the JSON string to the model

    //        String[] participantsJckson = new String[participatedMembers.size()];
    //        for (int i = 0; i < participatedMembers.size(); i++) {
    //            participantsJckson[i] = participatedMembers.get(i).getFirstName() + " " + participatedMembers.get(i).getLastName();
    //        }
    //
    //        System.out.println("LLLLLLLLLLLLIIIIIIIIIIIISSSSSTTTTTTTTTAAAAAAA     "+ Arrays.stream(participantsJckson).toList());
    //        ObjectMapper mapper = new ObjectMapper();
    //        String participantsJson = mapper.writeValueAsString(participatedMembers);
    //        model.addAttribute("participantsJckson", participantsJckson);
    //        System.out.println("Submitted items: @@@@@@@@@@@@@@@@    " + participantsJson);

    //        model.addAttribute("participantsJckson", participantsJckson);

    // Return a response if needed
    //        return "redirect:/detailCompetition?id="+competition.getId();
    //        return "tournament";
    return "matches";
  }

  @GetMapping("/pairMatches")
  public String matchPair(@RequestParam("idcomp") Long id, Model model) {
    Competition competition = competitionRepository.findById(id).get();
    List<Member> players = new ArrayList<>();
    players = competition.getParticipatedMembers();
    System.out.println(
      "PARTICIPANTS LIST SIZE: @######## =======>>>>>>>>    " + players.size()
    );

    Collections.shuffle(players);

    List<Match> matches = new ArrayList<>();
    for (int i = 0; i < players.size(); i += 2) {
      // Check if there's an odd player out
      if (i == players.size() - 1) {
        System.out.println(
          "Player without match: " + players.get(i).getFirstName()
        );
        break;
      }

      Match pair = Match
        .builder()
        .bluePlayer(players.get(i))
        .redPlayer(players.get(i + 1))
        .status(StatusMatch.PROGRAMMED)
        .competition(competition)
        .build();
      List<Score> scores = new ArrayList<>();
      for (int j = 0; j < 5; j++) {
        scores.add(
          Score
            .builder()
            .arbitreName("Arbitre" + (j + 1))
            .blueScore(0)
            .redScore(0)
            //                        .match(pair)
            //.id(Long.valueOf(j))
            //.match(Match.builder().match_id(Long.valueOf(123)).build())
            .build()
        );
      }
      //            pair.setScores(scores);
      //            matchRepository.save(pair);
      matches.add(pair);
    }
    matchRepository.saveAll(matches);
    model.addAttribute("matches", matches);
    return "versus";
  }
}
