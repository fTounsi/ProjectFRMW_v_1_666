package ma.anizar.frmw.controller;

import lombok.AllArgsConstructor;
import ma.anizar.frmw.model.Club;
import ma.anizar.frmw.model.Region;
import ma.anizar.frmw.repository.ClubRepository;
import ma.anizar.frmw.repository.RegionRepository;
import ma.anizar.frmw.service.ClubService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class ClubController {
    private ClubRepository clubRepository;
    private RegionRepository regionRepository;
    private final ClubService clubService;

    @GetMapping("/club")
    public String club(Model model){
        //Club
        List<Club> clubList= clubRepository.findAll();
        model.addAttribute("listClubs",clubList);
        return "club";
    }

    @GetMapping("/formClub")
    public String formClub(Model model){
        //Region
        List<Region> regionList= regionRepository.findAll();
        model.addAttribute("listRegions",regionList);
        model.addAttribute("club", new Club());
        return "formClub";
    }

    @PostMapping("/saveClub")
    public String saveClub(@ModelAttribute Club club, Model model, RedirectAttributes redirectAttributes){
        String response = clubService.addClub(club);
        if (response.startsWith("Error")) {
            redirectAttributes.addFlashAttribute("errorMessage", response);
            model.addAttribute("errorMessage",response);
            System.out.println("############### ############### " + response);
            return "formClub";
        }
        if(club.getCreatedDate() == null){
            club.setCreatedDate(new Date());
        }
        model.addAttribute("successMessage",response);
        redirectAttributes.addFlashAttribute("successMessage", response);
        return "redirect:/club";
    }

    @GetMapping("/editClub")
    public String editClub(Model model, @RequestParam(name = "id") Long id){
        //Region
        List<Region> regionList= regionRepository.findAll();
        model.addAttribute("listRegions",regionList);
        Club club= clubRepository.findById(id).get();
        model.addAttribute("club",club);
        return "editClub";
    }

    @GetMapping("/detailClub")
    public String detailClub(Model model, @RequestParam(name = "id") Long id){
        Club club= clubRepository.findById(id).get();
        model.addAttribute("club",club);
        return "detailClub";
    }
}
