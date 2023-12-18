package ma.anizar.frmw.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String arbitreName ;
    private int redScore;
    private int blueScore;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "match_id")
    private Match match;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Score other = (Score) obj;
        return  id == other.id && arbitreName == other.arbitreName && match.getMatch_id() == other.match.getMatch_id();
    }

    @Override
    public int hashCode() {
        // Utilisez Objects.hash pour générer un code de hachage basé sur les attributs
        return Objects.hash(id, arbitreName,match.getMatch_id());
    }
}
