package ma.anizar.frmw.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.anizar.frmw.model.enums.StatusRound;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoundDTO {

  private Long id;
  private String name;
  private int orderRound;
  private StatusRound status;
  private MatchDTO match;
  private boolean curent;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  List<ScoreDTO> scores = new ArrayList<>();
}
