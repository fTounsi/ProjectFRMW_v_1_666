package ma.anizar.frmw.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {

  private Long id;
  private String firstName;
  private String lastName;
  private String clubName;
  //private ClubDTO club;
}
