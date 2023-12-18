package ma.anizar.frmw.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor @Builder @Setter @Getter
public class Club {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long club_id;
    private Long code;
    private String name;
    private String address;
    private String telephone;
    private String logoPath;

    //@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdDate;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateAssembleeG;

    private String president;
    private String cinPresident;
    private String telPresident;

    private String entraineur;
    private String cinEntraineur;
    private String telEntraineur;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "club", cascade = CascadeType.ALL)
    private List<Member> memberList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "region_id")
    private Region region;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "responsable_id")
//    private Utilisateur responsable;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "club", cascade = CascadeType.ALL)
    private List<Utilisateur> users;

    @ManyToMany(mappedBy = "participatedClubs", fetch = FetchType.EAGER)
    List<Competition> competitions;

//    public Club(Date createdDate){
//        this.setCreatedDate(createdDate);
//    }
}
