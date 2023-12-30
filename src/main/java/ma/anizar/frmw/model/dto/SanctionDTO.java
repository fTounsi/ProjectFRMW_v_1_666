package ma.anizar.frmw.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.anizar.frmw.model.enums.TypeSanction;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanctionDTO {

  private Long id;
  private TypeSanction typeSanction;
  private RoundDTO round;
  private MemberDTO player;
}
