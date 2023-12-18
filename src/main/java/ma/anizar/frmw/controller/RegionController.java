package ma.anizar.frmw.controller;

import lombok.AllArgsConstructor;
import ma.anizar.frmw.model.Region;
import ma.anizar.frmw.repository.RegionRepository;
import ma.anizar.frmw.service.RegionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
public class RegionController {
    private RegionRepository regionRepository;
    private final RegionService regionService;

    @GetMapping("/region")
    public String region(Model model){
        //Region
        List<Region> regionList= regionRepository.findAll();
        model.addAttribute("listRegions",regionList);
        return "region";
    }

    @GetMapping("/formRegion")
    public String formRegion(Model model){
        model.addAttribute("region", new Region());
        return "formRegion";
    }

    @PostMapping("/saveRegion")
    public String formRegion(@ModelAttribute Region region, Model model, RedirectAttributes redirectAttributes){
        String response = regionService.addRegion(region);
        if (response.startsWith("Error")) {
            redirectAttributes.addFlashAttribute("errorMessage", response);
            model.addAttribute("errorMessage",response);
            System.out.println("############### ############### " + response);
            return "formRegion";
        }
        model.addAttribute("successMessage",response);
        redirectAttributes.addFlashAttribute("successMessage", response);
        return "redirect:/region";
    }

    @GetMapping("/editRegion")
    public String editRegion(Model model , @RequestParam(name = "id") Long id) {
        Region region = regionRepository.findById(id).get();
        model.addAttribute("region", region);
        return "editRegion";
    }

    @GetMapping("/detailRegion")
    public String detailRegion(Model model , @RequestParam(name = "id") Long id) {
        Region region = regionRepository.findById(id).get();
        model.addAttribute("region", region);
        return "detailRegion";
    }

}
