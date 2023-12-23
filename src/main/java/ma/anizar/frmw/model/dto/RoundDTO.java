package ma.anizar.frmw.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.anizar.frmw.model.enums.StatusRound;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoundDTO {

    private Long id;
    private String name;
    private int order;
    private StatusRound status;
    private MatchDTO match;

    List<ScoreDTO> scores = new ArrayList<>();

}
