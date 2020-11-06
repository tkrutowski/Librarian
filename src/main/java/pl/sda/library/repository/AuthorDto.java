package pl.sda.library.repository;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import pl.sda.library.model.Author;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "authors")
class AuthorDto {
    @Id
    @Column(name = "id_author")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    public Author toModel() {
        Author author = new Author();
        author.setId(getId());
        author.setFirstName(getFirstName());
        author.setLastName(getLastName());
        return author;
    }
}
