package ma.anizar.frmw.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.*;
import ma.anizar.frmw.model.dto.*;
import ma.anizar.frmw.model.enums.StatusMatch;

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
    private Long match_id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Competition competition;

    @ManyToOne(cascade = CascadeType.ALL)
    private Member redPlayer;

    @ManyToOne(cascade = CascadeType.ALL)
    private Member bluePlayer;

    private StatusMatch status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

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
                    roundsDto.add(
                            RoundDTO
                                    .builder()
                                    .id(r.getId())
                                    .name(r.getName())
                                    .status(r.getStatus())
                                    .order(r.getOrder())
                                    .match(
                                            MatchDTO.builder().match_id(r.getMatch().getMatch_id()).build()
                                    )
                                    .build()
                    );
                });
        return MatchDTO
                .builder()
                .match_id(this.match_id)
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
                .endTime(this.endTime)
                .startTime(this.startTime)
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
        this.startTime = startTime;
    }
}
