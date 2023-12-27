package ma.anizar.frmw.model;


import jakarta.persistence.*;
import lombok.*;
import ma.anizar.frmw.model.enums.TypeSanction;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Sanction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private TypeSanction typeSanction;

    @ManyToOne
    @JoinColumn(name = "round_id", referencedColumnName = "id")
    private Round round;
}
