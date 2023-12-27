package ma.anizar.frmw.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.anizar.frmw.model.enums.TypeSanction;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanctionDTO {

  private Long id;

  private String arbitreName;
  private TypeSanction redSanction;
  private TypeSanction blueSanction;
  private RoundDTO round;

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    SanctionDTO other = (SanctionDTO) obj;
    return (
      id == other.id &&
      arbitreName == other.arbitreName &&
      round.getId() == other.round.getId()
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, arbitreName, round.getId());
  }
}
