package pl.sda.library.repository;

import lombok.*;

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
    private int idBookstore;
    private String name;
    private String www;
//
//    @OneToMany(mappedBy = "books", fetch = FetchType.LAZY)
//    private Set<BookEntity> books = new HashSet<>();
}
