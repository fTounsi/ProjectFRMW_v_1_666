package ma.anizar.frmw.controller;

import lombok.AllArgsConstructor;
import ma.anizar.frmw.model.Club;
import ma.anizar.frmw.model.Member;
import ma.anizar.frmw.repository.ClubRepository;
import ma.anizar.frmw.repository.MemberRepository;
import ma.anizar.frmw.service.FileStorageService;
import ma.anizar.frmw.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
public class MemberController {
    @Autowired
    private FileStorageService fileStorageService;
    private ClubRepository clubRepository;
    private MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/member")
    public String member(Model model){
        //Member
        List<Member> memberList= memberRepository.findAll();
        model.addAttribute("listMembers",memberList);
        return "member";
    }

    @GetMapping("/formMember")
    public String formMember(Model model){
        //Club
        List<Club> clubList= clubRepository.findAll();
        model.addAttribute("listClubs",clubList);
        model.addAttribute("member", new Member());
        return "formMember";
    }

    @PostMapping("/saveMember")
    public String saveMember(@ModelAttribute Member member,Model model, @RequestParam("picture") MultipartFile multipartFile, RedirectAttributes redirectAttributes){
        try {
            String avatarUrl = fileStorageService.storeFile(multipartFile);
            System.out.println("@@@@@@@@@@@################# AVATAAAR  "+avatarUrl);
            member.setPhotoPath(avatarUrl);
        }catch (IOException exception){
            System.out.println("Exception:  "+exception.getMessage());
        }

        String response = memberService.addMember(member);
        if (response.startsWith("Error")) {
            //Club
            List<Club> clubList= clubRepository.findAll();
            model.addAttribute("listClubs",clubList);
            redirectAttributes.addFlashAttribute("errorMessage", response);
            model.addAttribute("errorMessage",response);
            System.out.println("############### ############### " + response);
            return "formMember";
        }
        model.addAttribute("successMessage",response);
        redirectAttributes.addFlashAttribute("successMessage", response);
        return "redirect:/member"; // redirect to a listing page or confirmation page
        /*redirectAttributes.addFlashAttribute("message", "Erreur...");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        Member mcin =memberRepository.findByCin(member.getCin());
        try {
            if(member.getCin().equals(mcin.getCin())){
                redirectAttributes.addFlashAttribute("message", "Erreur, ce CIN est déjà existant");
                return "redirect:/formMember";
            }
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            member.setPhotoPath(fileName);
            Member savedMember =memberRepository.save(member);
            String uploadDir = "user-photos/" + savedMember.getId();
            //FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        }catch (Exception exception){
            System.out.println("############     Error Saving Member     #############");
            System.out.println(exception.getMessage());
            System.out.println("##################     END Error     ####################");
            return "redirect:/formMember";
        }
        redirectAttributes.addFlashAttribute("message", "Adhérent ajouté avec succes.");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/member";*/
    }

    @GetMapping("/editMember")
    public String editMember(Model model, @RequestParam(name = "id") Long id){
        //Club
        List<Club> clubList= clubRepository.findAll();
        model.addAttribute("listClubs",clubList);
        Member member= memberRepository.findById(id).get();
        model.addAttribute("member",member);
        return "editMember";
    }

    @GetMapping("/detailMember")
    public String detailMember(Model model, @RequestParam(name = "id") Long id){
        Member member= memberRepository.findById(id).get();
        model.addAttribute("member",member);
        return "detailMember";
    }


    @PostMapping("/updateMember")
    public String updateMember(@ModelAttribute Member member,Model model, @RequestParam("picture") MultipartFile multipartFile, RedirectAttributes redirectAttributes) {
        try {
            String avatarUrl = fileStorageService.storeFile(multipartFile);
            System.out.println("@@@@@@@@@@@################# AVATAAAR  "+avatarUrl);
            member.setPhotoPath(avatarUrl);
        }catch (IOException exception){
            System.out.println("Exception:  "+exception.getMessage());
        }
        String response = memberService.updateMember(member);
        if (response.startsWith("Error")) {
            //Club
            List<Club> clubList = clubRepository.findAll();
            model.addAttribute("listClubs", clubList);
            redirectAttributes.addFlashAttribute("errorMessage", response);
            model.addAttribute("errorMessage", response);
            System.out.println("############### ############### " + response);
            return "editMember";
        }
        model.addAttribute("successMessage", response);
        redirectAttributes.addFlashAttribute("successMessage", response);
        return "redirect:/member"; // redirect to a listing page or confirmation page
    }
}
