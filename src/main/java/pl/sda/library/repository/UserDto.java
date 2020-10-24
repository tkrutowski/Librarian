package pl.sda.library.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private int idUser;
    private String login;
    private String password;
    private String name;
    @Column(name = "is_admin")
    boolean isAdmin;
    //@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)//relacja dwukierunkowa
    //private Set<BookEntity> books;
}
