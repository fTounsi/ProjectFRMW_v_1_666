package ma.anizar.frmw.dto;

import lombok.Data;

@Data
public class Participant {

    private Long id;
    private String firstName;
    private String lastName;
    private String cin;
    private String photoPath;
}
