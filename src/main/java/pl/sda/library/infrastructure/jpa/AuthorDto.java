package pl.sda.library.infrastructure.jpa;

import lombok.Data;
import pl.sda.library.domain.model.Author;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
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

    Author toDomain() {
        Author author = new Author();
        author.setId(getId());
        author.setFirstName(getFirstName());
        author.setLastName(getLastName());
        return author;
    }

    static AuthorDto fromDomain(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setFirstName(author.getFirstName());
        authorDto.setLastName(author.getLastName());
        return authorDto;
    }
}
