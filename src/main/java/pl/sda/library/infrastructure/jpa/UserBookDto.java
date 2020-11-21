package pl.sda.library.infrastructure.jpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.sda.library.domain.model.EditionType;
import pl.sda.library.domain.model.OwnershipStatus;
import pl.sda.library.domain.model.ReadingStatus;
import pl.sda.library.domain.model.UserBook;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "userbook")
public class UserBookDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_book")
    private BookDto book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private UserDto user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_bookstore")
    private BookstoreDto bookstore;

    @Column(name = "edition_type")
    @Enumerated(EnumType.STRING)
    private EditionType editionType;

    @Column(name = "reading_status")
    @Enumerated(EnumType.STRING)
    private ReadingStatus readingStatus;

    @Column(name = "ownership_status")
    @Enumerated(EnumType.STRING)
    private OwnershipStatus ownershipStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "read_from")
    private Date readFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "read_to")
    private Date readTo;

    @Column(name = "is_read")
    private Boolean isRead;

    private String info;

    UserBook toDomain() {
        UserBook userBook = new UserBook();
        userBook.setId(getId());
        userBook.setIdBook(book.getId());
        userBook.setIdUser(getUser().getId());
        userBook.setBookstore(getBookstore().getName());
        userBook.setEditionType(getEditionType());
        userBook.setReadingStatus(getReadingStatus());
        userBook.setOwnershipStatus(getOwnershipStatus());
        userBook.setReadFrom(getReadFrom());
        userBook.setReadTo(getReadTo());
        userBook.setInfo(getInfo());
        userBook.setIsRead(getIsRead());
        return userBook;
    }
}
