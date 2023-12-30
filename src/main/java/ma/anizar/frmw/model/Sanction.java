package ma.anizar.frmw.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

  private TypeSanction typeSanction;

  @ManyToOne
  @JoinColumn(name = "round_id", referencedColumnName = "id")
  private Round round;

  @ManyToOne(cascade = CascadeType.MERGE)
  private Member player;
}
