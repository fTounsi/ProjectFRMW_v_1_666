package ma.anizar.frmw.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import ma.anizar.frmw.dto.UtilisateurDTO;
import ma.anizar.frmw.model.Club;
import ma.anizar.frmw.model.Member;
import ma.anizar.frmw.model.Role;
import ma.anizar.frmw.model.Utilisateur;
import ma.anizar.frmw.repository.ClubRepository;
import ma.anizar.frmw.repository.UtilisateurRepository;
import ma.anizar.frmw.service.FileStorageService;
import ma.anizar.frmw.service.RoleService;
import ma.anizar.frmw.service.UtilisateurService;
import ma.anizar.frmw.service.UtilisateurServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Controller
public class UtilisateurController {

    @Autowired
    private FileStorageService fileStorageService;
    private final UtilisateurRepository utilisateurRepository;
    private static final int PAGE_SIZE = 10;

    private UtilisateurService utilisateurService;
    private final UtilisateurServiceImplementation utilisateurServiceImplementation;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;
    private ClubRepository clubRepository;


    //@GetMapping("/add")
    public String showEmployeePage(Model model) {
        model.addAttribute("heading", "Add New Employee");
        model.addAttribute("employee", new UtilisateurDTO());
        model.addAttribute("username", utilisateurService.getLoggedInUserUsername());
        return "employee";
    }


    //@PostMapping("/save")
    public String saveEmployee(Model model, @ModelAttribute("employee") UtilisateurDTO utilisateurDTO, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String referer = request.getHeader("Referer");
        if (referer.contains("add")) {
            if (utilisateurService.existsByLogin(utilisateurDTO.getLogin())) {
                redirectAttributes.addFlashAttribute("error", "Email ID: " + utilisateurDTO.getLogin() + " already exists!");
                return "redirect:" + referer;
            } else if (utilisateurDTO.getPassword() == null || utilisateurDTO.getPassword().equals("")) {
                redirectAttributes.addFlashAttribute("error", "Please enter password!");
                return "redirect:" + referer;
            } else {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setFirstName(utilisateurDTO.getName());
//                utilisateur.setLastName(utilisateurDTO.getLastName());
                utilisateur.setLogin(utilisateurDTO.getLogin());
                utilisateur.setPassword(passwordEncoder.encode(utilisateurDTO.getPassword()));
                utilisateur.setRoles(new ArrayList<Role>());
//                employeeService.saveEmployee(employee);
//                Role role = roleService.findByName(utilisateurDTO.getType()).get();
                List<Role> roles = new ArrayList<>();
                //roles.add(role);
                utilisateur.setRoles(roles);
                utilisateurService.saveUtilisateur(utilisateur);
                redirectAttributes.addFlashAttribute("success", "Employee added successfully");
                return "redirect:/";
            }
        }
        // else for update
        Utilisateur utilisateur = utilisateurService.getUtilisateur(utilisateurDTO.getId());
        // check if updated email already is assigned to someone else
        if (!utilisateurDTO.getLogin().equals(utilisateur.getLogin()) && utilisateurService.existsByLogin(utilisateurDTO.getLogin())) {
            redirectAttributes.addFlashAttribute("error", "Email ID: " + utilisateurDTO.getLogin() + " already exists!");
            return "redirect:" + referer;
        }
        utilisateur.setFirstName(utilisateurDTO.getName());
//        utilisateur.setLastName(utilisateurDTO.getLastName());
        utilisateur.setLogin(utilisateurDTO.getLogin());
        if (utilisateurDTO.getPassword() == null || utilisateurDTO.getPassword().length() == 0)
            utilisateur.setPassword(utilisateurService.getUtilisateur(utilisateurDTO.getId()).getPassword());
        else
            utilisateur.setPassword(passwordEncoder.encode(utilisateurDTO.getPassword()));
        //utilisateur.setSalary(employeeDTO.getSalary());
        //Role role = roleService.findByName(utilisateurDTO.getType()).get();
        List<Role> roles = new ArrayList<>();
       // roles.add(role);
        utilisateur.setRoles(roles);
        utilisateurService.saveUtilisateur(utilisateur);
        redirectAttributes.addFlashAttribute("success", "Employee with email ID: " + utilisateur.getLogin() + " updated successfully");
        return "redirect:" + referer;
    }

    //@GetMapping("/update/{id}")
    public String showUpdateEmployeePage(@PathVariable Long id, Model model, HttpServletRequest request) {
        Utilisateur utilisateur = utilisateurService.getUtilisateur(id);
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setId(utilisateur.getId());
        utilisateurDTO.setName(utilisateur.getFirstName());
//        utilisateurDTO.setLastName(utilisateur.getLastName());
        utilisateurDTO.setLogin(utilisateur.getLogin());
        utilisateurDTO.setPassword(null);
        //employeeDTO.setSalary(utilisateur.getSalary());
        //final Role ROLE_ADMIN = roleService.findByName("ADMIN").get();
//        if (utilisateur.getRoles().contains(ROLE_ADMIN))
//            utilisateurDTO.setType("ADMIN");
//        else
//            utilisateurDTO.setType("USER");
        model.addAttribute("heading", "Update Employee");
        model.addAttribute("passwordHelp", "dummy_text");
        model.addAttribute("employee", utilisateurDTO);
        model.addAttribute("username", utilisateurService.getLoggedInUserUsername());
        return "employee";
    }

    //@GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Utilisateur utilisateur = utilisateurService.getUtilisateur(id);
        utilisateur.setRoles(new ArrayList<>());
        utilisateurService.saveUtilisateur(utilisateur);
        utilisateurService.deleteUtilisateur(id);
        redirectAttributes.addFlashAttribute("error", "Employee deleted with email ID: " + utilisateur.getLogin());
        return "redirect:/";
    }

    @GetMapping("/user")
    public String user(Model model){
        //Member
        List<Utilisateur> utilisateurList= utilisateurRepository.findAll();
        model.addAttribute("listUsers",utilisateurList);
        return "user";
    }

    @PostMapping("/saveUserr")
    public String createUser(@RequestParam("login") String login,
                             @RequestParam("password") String password,
                             @RequestParam("name") String name,
                             @RequestParam("role") String role,
                             @RequestParam("avatar") MultipartFile avatarFile) {
        try {
            String avatarUrl = fileStorageService.storeFile(avatarFile);
            // Save user with avatarUrl
            System.out.println("Save user with avatarUrl   "+avatarUrl);
            Utilisateur utilisateur= new Utilisateur();
            utilisateur.setLogin(login);
            utilisateur.setFirstName(name);
            utilisateur.setPassword(passwordEncoder.encode(password));
            utilisateur.setPhotoPath(avatarUrl);
            Role userRole = roleService.findByName(role);

            if (userRole != null) {
                if (utilisateur.getRoles() == null) {
                    List<Role> roles = new ArrayList<>();
                    roles.add(userRole);
                    utilisateur.setRoles(roles);
                } else {
                    utilisateur.getRoles().add(userRole);
                }
            }


            utilisateurRepository.save(utilisateur);
            return "redirect:/user";
        } catch (IOException e) {
            // Handle the exception, e.g., return an error view
            System.out.println("##########      ERROR     @@@@@   "+e.getMessage());
            return "redirect:/user";
        }
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute Utilisateur utilisateur,Model model,
                           @RequestParam("role") Long roleId,
                           @RequestParam("password") String password,
                           @RequestParam("avatar") MultipartFile multipartFile, RedirectAttributes redirectAttributes) {

        Role role = roleService.findById(roleId);
        if (role != null) {
            utilisateur.setRoles(Arrays.asList(role)); // Or however you need to set the role
        } else {
            // Handle case where role is not found
        }
        try {
            String avatarUrl = fileStorageService.storeFile(multipartFile);
            System.out.println("@@@@@@@@@@@################# AVATAAAR  "+avatarUrl);
            utilisateur.setPhotoPath(avatarUrl);
        }catch (IOException exception){
            System.out.println("Exception:  "+exception.getMessage());
        }
        utilisateur.setPassword(passwordEncoder.encode(password));
        String response = utilisateurServiceImplementation.addUtilisateur(utilisateur);
        if (response.startsWith("Error")) {
            //Club
            List<Club> clubList= clubRepository.findAll();
            model.addAttribute("listClubs",clubList);
            model.addAttribute("member", new Member());
            //Role
            List<Role> roleList = roleService.findAll();
            model.addAttribute("listRoles", roleList);
            redirectAttributes.addFlashAttribute("errorMessage", response);
            return "formUser";
        }
        redirectAttributes.addFlashAttribute("successMessage", response);
        return "redirect:/user"; // redirect to a listing page or confirmation page
    }

    @GetMapping("/formUser")
    public String formUser(Model model){
        //Club
        List<Club> clubList= clubRepository.findAll();
        model.addAttribute("listClubs",clubList);
        model.addAttribute("member", new Member());
        //Roles
        List<Role> roleList= roleService.findAll();
        model.addAttribute("listRoles",roleList);
        model.addAttribute(new Utilisateur());
        return "formUser";
    }

    @GetMapping("/editUser")
    public String editMember(Model model, @RequestParam(name = "id") Long id){
        //Club
        List<Club> clubList= clubRepository.findAll();
        model.addAttribute("listClubs",clubList);
        model.addAttribute("member", new Member());
        //Roles
        List<Role> roleList= roleService.findAll();
        model.addAttribute("listRoles",roleList);
        Utilisateur utilisateur= utilisateurRepository.findById(id).get();
        model.addAttribute("utilisateur",utilisateur);
        return "editUser";
    }
    private String storeAvatarFile(MultipartFile file) {
        // Implement the logic to store the file and return the URL
        // This could involve saving the file locally or to a cloud storage service
        return "url_of_the_stored_avatar";
    }

    @GetMapping("/profile")
    public String detailUser(Model model, @RequestParam(name = "id") Long id){
        Utilisateur utilisateur= utilisateurRepository.findById(id).get();
        model.addAttribute("utilisateur",utilisateur);
        return "profile";
    }

}