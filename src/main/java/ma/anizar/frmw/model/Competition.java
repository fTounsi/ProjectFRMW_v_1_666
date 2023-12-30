package ma.anizar.frmw.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
  private Collection<Registration> registrations = new ArrayList<>();

  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "competition",
    cascade = CascadeType.ALL
  )
  List<Match> matches;
}
