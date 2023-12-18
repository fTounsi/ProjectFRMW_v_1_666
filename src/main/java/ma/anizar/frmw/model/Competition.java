package ma.anizar.frmw.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startDate;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date endingDate;
    private String place;
    private String status;
    private String typeComp;

    @ManyToMany
    List<Club> participatedClubs;

    @ManyToMany
//    List<Member> participants;
    List<Member> participatedMembers;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "competitions")
    private Collection<Registration> registrations= new ArrayList<>();

    @ManyToMany
    List<Match> matches;
}
