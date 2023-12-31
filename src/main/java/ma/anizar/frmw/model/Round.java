package ma.anizar.frmw.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import ma.anizar.frmw.model.enums.StatusRound;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Round {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JoinColumn(name = "name")
  private String name;

  @JoinColumn(name = "orderRound")
  private int orderRound;

  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "round",
    cascade = CascadeType.ALL
  )
  List<Score> scores = new ArrayList<>();

  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "round",
    cascade = CascadeType.ALL
  )
  List<Sanction> sanctions = new ArrayList<>();

  @JoinColumn(name = "status")
  private StatusRound status;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "match_id")
  private Match match;

  private LocalDateTime startTime;

  private LocalDateTime endTime;
}
