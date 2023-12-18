package ma.anizar.frmw.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor @Builder @Setter
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String cin;
    //@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;
    private String address;
    private String telephone;
    private String email;
    private String gender;
    private int height;
    private int weight;
    private String photoPath;
    //private Passport passport;
    private String clubName;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "club_id")
    private Club club;

    @ManyToMany(mappedBy = "participatedMembers", fetch = FetchType.EAGER)
    List<Competition> competitions;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Registration> registrations = new ArrayList<>();

    @Transient
    public String getPhotosImagePath() {
        if (photoPath == null || id == null) return null;
        return "/user-photos/" + id + "/" + photoPath;
    }

    public Member(String firstName,String lastName,Date birthDate,String address,String telephone, String email, String gender){
        this.firstName=firstName;
        this.lastName=lastName;
        this.birthDate=birthDate;
        this.address=address;
        this.telephone=telephone;
        this.email=email;
        this.gender=gender;
    }

}