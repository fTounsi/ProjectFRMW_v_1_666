package ma.anizar.frmw.model;


import jakarta.persistence.*;
import lombok.*;
import ma.anizar.frmw.model.enums.StatusRound;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class  Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int roud_order;
    private StatusRound status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "match_id")
    private Match match;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "round",
            cascade = CascadeType.ALL
    )
     List<Score> scores = new ArrayList<>();


}
