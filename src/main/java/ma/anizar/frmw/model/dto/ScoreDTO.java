package ma.anizar.frmw.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreDTO {

  private Long id;

  private String arbitreName;
  private int redScore;
  private int blueScore;
  private MatchDTO match;

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    ScoreDTO other = (ScoreDTO) obj;
    return (
      id == other.id &&
      arbitreName == other.arbitreName &&
      match.getMatch_id() == other.match.getMatch_id()
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, arbitreName, match.getMatch_id());
  }
}
