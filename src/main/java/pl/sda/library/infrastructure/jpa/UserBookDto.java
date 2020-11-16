package pl.sda.library.infrastructure.jpa;

import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.model.EditionType;
import pl.sda.library.domain.model.OwnershipStatus;
import pl.sda.library.domain.model.ReadingStatus;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class UserBookDto {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @ManyToOne
        @JoinColumn(name = "id_book")
        private BookDto book;

        @ManyToOne
        @JoinColumn(name = "id_user")
        private UserDto user;

        @ManyToOne
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

        @Column(name = "read_from")
        private LocalDate readFrom;

        @Column(name = "read_to")
        private LocalDate readTo;

        @Column(name = "is_read")
        private Boolean isRead;

        private String info;

 /*       Book toDomain() {
            Book book = new Book();
            book.setIdBook(getId());
            // book.setUserLogin(getUser().getLogin());
            // book.setBookstore(getBookstore().getName());
            book.setSeries(series == null? "": getSeries().getTitle());
            book.setAuthors(getAuthorsAsString());
            book.setCategories(getCategoriesAsString());
            book.setTitle(getTitle());
            book.setSubtitle(getSubtitle());
            book.setDescription(getDescription());
            book.setCover(getCover());
//        book.setEditionType(getEditionType());
//        book.setReadingStatus(getReadingStatus());
//        book.setOwnershipStatus(getOwnershipStatus());
//        book.setReadFrom(getReadFrom());
//        book.setReadTo(getReadTo());
//        book.setInfo(getInfo());
//        book.setIsRead(getIsRead());
            // book.setIsbn(getIsbn());
            book.setBookInSeriesNo(getVolume());
            return book;
        }

        static pl.sda.library.infrastructure.jpa.BookDto fromDomain(Book book) {
            pl.sda.library.infrastructure.jpa.BookDto bookDto = new pl.sda.library.infrastructure.jpa.BookDto();
            bookDto.setId(book.getIdBook());
            bookDto.setSeries(getSeriesFromString(book.getSeries()));
//        bookDto.setAuthors(getAuthorsFromString(book.getAuthors()));
//        bookDto.setCategories(getCategoriesFromString(book.getCategories()));
            bookDto.setTitle(book.getTitle());
            bookDto.setSubtitle(book.getSubtitle());
            bookDto.setDescription(book.getDescription());
            bookDto.setCover(book.getCover());

            bookDto.setVolume(book.getBookInSeriesNo());
            return bookDto;
        }*/
}
