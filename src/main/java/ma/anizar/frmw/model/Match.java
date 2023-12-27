package ma.anizar.frmw.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import ma.anizar.frmw.model.dto.*;
import ma.anizar.frmw.model.enums.StatusMatch;
import ma.anizar.frmw.model.enums.StatusRound;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "matches")
public class Match {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long matchId;

  @ManyToOne(cascade = CascadeType.ALL)
  private Competition competition;

  @ManyToOne(cascade = CascadeType.ALL)
  private Member redPlayer;

  @ManyToOne(cascade = CascadeType.ALL)
  private Member bluePlayer;

  private StatusMatch status;

  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "match",
    cascade = CascadeType.ALL
  )
  List<Round> rounds = new ArrayList();

  public MatchDTO toDTO() {
    List<RoundDTO> roundsDto = new ArrayList();
    this.rounds.stream()
      .forEach(r -> {
        RoundDTO roundDTO = RoundDTO
          .builder()
          .id(r.getId())
          .name(r.getName())
          .status(r.getStatus())
          .orderRound(r.getOrderRound())
          .match(MatchDTO.builder().match_id(r.getMatch().getMatchId()).build())
          .curent(StatusRound.IN_PROGRESS == r.getStatus())
          .endTime(r.getEndTime())
          .startTime(r.getStartTime())
          .build();
        List<ScoreDTO> scoresDto = new ArrayList();
        r.scores
          .stream()
          .forEach(score -> {
            scoresDto.add(
              ScoreDTO
                .builder()
                .id(score.getId())
                .blueScore(score.getBlueScore())
                .redScore(score.getRedScore())
                .arbitreName(score.getArbitreName())
                .round(RoundDTO.builder().id(r.getId()).build())
                .build()
            );
          });

        roundDTO.setScores(scoresDto);
        roundsDto.add(roundDTO);
      });
    return MatchDTO
      .builder()
      .match_id(this.matchId)
      .competition(
        CompetitionDTO.builder().name(this.competition.getName()).build()
      )
      .bluePlayer(
        MemberDTO
          .builder()
          .firstName(this.bluePlayer.getFirstName())
          .lastName(this.bluePlayer.getLastName())
          .build()
      )
      .redPlayer(
        MemberDTO
          .builder()
          .firstName(this.redPlayer.getFirstName())
          .lastName(this.redPlayer.getLastName())
          .build()
      )
      .rounds(roundsDto)
      .status(this.getStatus())
      .build();
  }

  public Match(
    Competition competition,
    Member redPlayer,
    Member bluePlayer,
    StatusMatch status,
    LocalDateTime startTime
  ) {
    this.competition = competition;
    this.redPlayer = redPlayer;
    this.bluePlayer = bluePlayer;
    this.status = status;
  }
}
