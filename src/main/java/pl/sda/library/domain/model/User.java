package pl.sda.library.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private Long idUser;
    private String login;
    private String password;
    private String name;
    private Boolean isAdmin;
}
