package ma.anizar.frmw.controller;

import lombok.AllArgsConstructor;
import ma.anizar.frmw.model.Club;
import ma.anizar.frmw.model.Competition;
import ma.anizar.frmw.model.Member;
import ma.anizar.frmw.model.Utilisateur;
import ma.anizar.frmw.repository.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class StaffController {

    private ClubRepository clubRepository;
    private RegionRepository regionRepository;
    private CompetitionRepository competitionRepository;
    private UtilisateurRepository utilisateurRepository;
    private MemberRepository memberRepository;

    @GetMapping("/staffclub")
    public String club(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Utilisateur connectedUser = utilisateurRepository.findUByLogin(username);
        Club club = connectedUser.getClub();
        System.out.println("###======   CLUB   ========>>>>>>>>>>>>        "+club.getName());
        model.addAttribute("myClub", club);

        return "/staffclub";
    }

    @GetMapping("/staffcompetition")
    public String staffcompetition(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Utilisateur connectedUser = utilisateurRepository.findUByLogin(username);
        Club club = connectedUser.getClub();
        System.out.println("###======   CLUB   ========>>>>>>>>>>>>        "+club.getName());
        System.out.println("###=================   ComETITIOZZZ   ========>>>>>>>>>>>>        "+club.getCompetitions().size());
        model.addAttribute("myComp", club.getCompetitions());

        return "/staffcompetition";
    }


    @GetMapping("/detailStaffCompetition")
    public String staffcomp(Model model, @RequestParam(name = "id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Utilisateur connectedUser = utilisateurRepository.findUByLogin(username);
        Club club = connectedUser.getClub();
        System.out.println("###======   CLUB   ========>>>>>>>>>>>>        "+club.getName());
        model.addAttribute("currentComp", competitionRepository.findById(id).get());
        model.addAttribute("participated_members", club.getMemberList());

        return "detailStaffCompetition";
    }

    @PostMapping("/addPntToComp/{id}")
    public String handleMultiSelect(@PathVariable Long id, @RequestBody Map<String, Object> payload, Model model) {
        List<String> selectedValues = (List<String>) payload.get("selectedValues");
        System.out.println("################  "+selectedValues.stream().toList());
        Competition competition= competitionRepository.findById(id).get();
        List<Member> participatedMembers = new ArrayList<>();
        for (String str : selectedValues) {
            Member m=memberRepository.findById(Long.parseLong(str)).get();
            participatedMembers.add(m);
        }
        competition.getParticipatedMembers().addAll(participatedMembers);
        competitionRepository.save(competition);
        model.addAttribute("comp",competition);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@     ^^^^^^^^^  "+competition.getParticipatedMembers().size());
        //return "redirect:/detailCompetition?id="+competition.getId();
        return "redirect:/detailStaffCompetition?id="+competition.getId();
    }

}
