package ma.anizar.frmw.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long region_id;

    private String label;
    private String code;
    private String siegeAdresse;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateAssembleeG;
    private String telephone;

    private String president;
    private String telPresident;
    private String secretaireG;
    private String telSecretaireG;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "region", cascade = CascadeType.ALL)
    private List<Club> clubList;

    public Region(String label, String code){
        this.label=label;
        this.code=code;
    }
}
