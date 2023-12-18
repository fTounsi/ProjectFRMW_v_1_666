package ma.anizar.frmw.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor @Builder @Setter @Getter
@Entity
@Table(name = "matches")
public class Match {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long match_id;
    private String competitionName;

    @ManyToOne(cascade = CascadeType.ALL)
    private Competition competition;
    @ManyToOne(cascade = CascadeType.ALL)
    private Member redPlayer;
    @ManyToOne(cascade = CascadeType.ALL)
    private Member bluePlayer;
    private StatusMatch status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "match", cascade = CascadeType.ALL)
    List<Score> scores =  new ArrayList();

}
