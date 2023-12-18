package ma.anizar.frmw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDTO {
    private Long id;
    private String name;
    private String avatarUrl;
    private String login;
    private String password;
    private Double salary;
    private String type;
    private String roles;
}