package ma.anizar.frmw.controller;

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

import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {
    private ClubRepository clubRepository;
    private RegionRepository regionRepository;
    private MemberRepository memberRepository;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/login";
    }

    @GetMapping("/")
    public String home(){return "redirect:/index";}

    @GetMapping("/index")
    public String index(){
        return "index";
    }


   /* @GetMapping("/region")
    public String region(Model model){
        //Region
        List<Region> regionList= regionRepository.findAll();
        model.addAttribute("listRegions",regionList);
        return "region";
    }*/

    //@GetMapping("/club")
    public String club(Model model){
        //Clubs
        List<Club> clubList= clubRepository.findAll();
        model.addAttribute("listClubs",clubList);
        //Region
        List<Region> regionList= regionRepository.findAll();
        model.addAttribute("listRegions",regionList);
        return "club";
    }

    //@GetMapping("/member")
    public String member(Model model){
        //Member
        List<Member> memberList= memberRepository.findAll();
        model.addAttribute("listMembers",memberList);
        return "member";
    }
    //@GetMapping("/competition")
    public String competition(Model model){
        //competition
        //List<Member> memberList= memberRepository.findAll();
        //model.addAttribute("listMembers",memberList);
        return "competition";
    }

    @GetMapping("/dash")
    public String compDash(){
        return "dash";
    }

    @GetMapping("/arbitre")
    public String arbitre(){
        return "arbitre";
    }
}
