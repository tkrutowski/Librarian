package pl.sda.library.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sda.library.domain.model.User;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
class UserDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;
    private String login;
    private String password;
    private String name;
    @Column(name = "is_admin")
    private Boolean isAdmin;
    //@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)//relacja dwukierunkowa
    //private Set<BookEntity> books;

    User toModel(){
        User user=new User();
        user.setIdUser(getIdUser());
        user.setLogin(getLogin());
        user.setPassword(getPassword());
        user.setName(getName());
        user.setIsAdmin(getIsAdmin());
        return user;
    }
}
