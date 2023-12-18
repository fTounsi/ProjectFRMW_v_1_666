package ma.anizar.frmw.model;


import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate registrationDate;

    @ManyToMany
    List<Competition> competitions;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "participant_id")
    private Member participant;
}
