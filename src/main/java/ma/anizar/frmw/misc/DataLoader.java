package ma.anizar.frmw.misc;

import lombok.AllArgsConstructor;
import ma.anizar.frmw.model.Role;
import ma.anizar.frmw.model.Utilisateur;
import ma.anizar.frmw.service.RoleService;
import ma.anizar.frmw.service.UtilisateurService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
//@Component
public class DataLoader implements ApplicationRunner {

    private UtilisateurService utilisateurService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;





    void createRandomUsers() {

        long UTILISATEURS_TO_BE_ADDED = 50L - utilisateurService.getUtilisateurCount().longValue();
        if (UTILISATEURS_TO_BE_ADDED <= 0) return;

        String[] _firstName =  new String[]{ "Adam", "Alex", "Aaron", "Alia", "Ben", "Carl", "Dan", "David", "Edward", "Fred", "Frank", "George", "Hal", "Hank", "Ike", "John", "Jack", "Joe", "Katie", "Larry", "Monte", "Matthew", "Mark", "Mary", "Nathan", "Otto", "Paul", "Peter", "Priya", "Roger", "Roger", "Steve", "Thomas", "Tim", "Ty", "Victor", "Walter"};
        String[] _lastName = new String[]{ "Anderson", "Ashwoon", "Aikin", "Bateman", "Bongard", "Bowers", "Boyd", "Cannon", "Cast", "Deitz", "Dewalt", "Ebner", "Frick", "Hancock", "Haworth", "Hesch", "Hoffman", "Kassing", "Knutson", "Lawless", "Lawicki", "Mccord", "McCormack", "Miller", "Myers", "Nugent", "Ortiz", "Orwig", "Ory", "Paiser", "Pak", "Pettigrew", "Quinn", "Quizoz", "Ramachandran", "Resnick", "Sagar", "Schickowski", "Schiebel", "Sellon", "Severson", "Shaffer", "Solberg", "Soloman", "Sonderling", "Soukup", "Soulis", "Stahl", "Sweeney", "Tandy", "Trebil", "Trusela", "Trussel", "Turco", "Uddin", "Uflan", "Ulrich", "Upson", "Vader", "Vail", "Valente", "Van Zandt", "Vanderpoel", "Ventotla", "Vogal", "Wagle", "Wagner", "Wakefield", "Weinstein", "Weiss", "Woo", "Yang", "Yates", "Yocum", "Zeaser", "Zeller", "Ziegler", "Bauer", "Baxster", "Casal", "Cataldi", "Caswell", "Celedon", "Chambers", "Chapman", "Christensen", "Darnell", "Davidson", "Davis", "DeLorenzo", "Dinkins", "Doran", "Dugelman", "Dugan", "Duffman", "Eastman", "Ferro", "Ferry", "Fletcher", "Fietzer", "Hylan", "Hydinger", "Illingsworth", "Ingram", "Irwin", "Jagtap", "Jenson", "Johnson", "Johnsen", "Jones", "Jurgenson", "Kalleg", "Kaskel", "Keller", "Leisinger", "LePage", "Lewis", "Linde", "Lulloff", "Maki", "Martin", "McGinnis", "Mills", "Moody", "Moore", "Napier", "Nelson", "Norquist", "Nuttle", "Olson", "Ostrander", "Reamer", "Reardon", "Reyes", "Rice", "Ripka", "Roberts", "Rogers", "Root", "Sandstrom", "Sawyer", "Schlicht", "Schmitt", "Schwager", "Schutz", "Schuster", "Tapia", "Thompson", "Tiernan", "Tisler" };
        String[] _type = new String[]{"USER", "ADMIN","STUFF"};
        String[] _passwords = new String[]{"$2a$10$os1EIh6FDFsCenAYN64PKuzQsgzAFAK0sCcyVOQ40Zr4J/K8MC5ia", "$2a$10$GNuGG/.Zz8xYWK0OzHIlL.Vv1tLwV1qUVS4aGrgMTubuyX4HtwT.i"};

        for (int i = 0; i < UTILISATEURS_TO_BE_ADDED; i++) {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setFirstName(_firstName[new Random().nextInt(_firstName.length)]);
            utilisateur.setLastName(_lastName[new Random().nextInt(_lastName.length)]);
            StringBuilder email = new StringBuilder();
            email.append(utilisateur.getFirstName());
            email.append(utilisateur.getLastName());
            email.append("@example.com");
            while (utilisateurService.existsByLogin(email.toString())) {
                email.insert(2, String.valueOf(new Random().nextInt(10)));
            }
            utilisateur.setLogin(email.toString().toLowerCase());
            String userType = _type[new Random().nextInt(2)];
            utilisateur.setPassword(userType.equals("USER" ) ? _passwords[0] : _passwords[1]);
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            //utilisateur.setSalary(Double.valueOf(decimalFormat.format(new Random().nextDouble(40000, 70000))));
            Role role = roleService.findByName(userType);
            List<Role> roles = new ArrayList<>();
            roles.add(role);
            utilisateur.setRoles(roles);
            utilisateurService.saveUtilisateur(utilisateur);
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
       // createRoles();
    }

}
