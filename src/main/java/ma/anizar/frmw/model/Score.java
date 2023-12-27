package ma.anizar.frmw.model;

import jakarta.persistence.*;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Score {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String arbitreName;
  private int redScore;
  private int blueScore;

  @ManyToOne
  @JoinColumn(name = "round_id", referencedColumnName = "id")
  private Round round;

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    Score other = (Score) obj;
    return (
      id == other.id &&
      arbitreName == other.arbitreName &&
      round.getId() == other.round.getId()
    );
  }
}
