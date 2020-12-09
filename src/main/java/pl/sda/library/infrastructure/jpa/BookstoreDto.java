package pl.sda.library.infrastructure.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.library.domain.model.Bookstore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "bookstores")
class BookstoreDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String url;

    Bookstore toDomain() {
        Bookstore bookstore = new Bookstore();
        bookstore.setId(getId());
        bookstore.setName(getName());
        bookstore.setUrl(getUrl());
        return bookstore;
    }

    static BookstoreDto fromDomain(Bookstore bookstore) {
        BookstoreDto bookstoreDto = new BookstoreDto();
        bookstoreDto.setId(bookstore.getId());
        bookstoreDto.setName(bookstore.getName());
        bookstoreDto.setUrl(bookstore.getUrl());
        return bookstoreDto;
    }
}
