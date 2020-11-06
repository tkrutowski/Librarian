package pl.sda.library.repository;

import lombok.*;
import pl.sda.library.model.Bookstore;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "bookstores")
class BookstoreDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bookstore")
    private Long idBookstore;
    private String name;
    private String www;

    public Bookstore toModel() {
        Bookstore bookstore = new Bookstore();
        bookstore.setIdBookstore(getIdBookstore());
        bookstore.setName(getName());
        bookstore.setWww(getWww());
        return bookstore;
    }
//
//    @OneToMany(mappedBy = "books", fetch = FetchType.LAZY)
//    private Set<BookEntity> books = new HashSet<>();
}
