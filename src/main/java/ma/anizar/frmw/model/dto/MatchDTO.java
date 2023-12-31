package ma.anizar.frmw.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import ma.anizar.frmw.model.enums.StatusMatch;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchDTO {

  private Long match_id;

  private CompetitionDTO competition;

  private MemberDTO redPlayer;

  private MemberDTO bluePlayer;

  private StatusMatch status;

  List<RoundDTO> rounds = new ArrayList();
}
