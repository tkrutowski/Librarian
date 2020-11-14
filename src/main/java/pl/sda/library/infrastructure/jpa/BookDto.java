package pl.sda.library.infrastructure.jpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.model.EditionType;
import pl.sda.library.domain.model.OwnershipStatus;
import pl.sda.library.domain.model.ReadingStatus;
import pl.sda.library.infrastructure.jpa.AuthorDto;
import pl.sda.library.repository.BookstoreDto;
import pl.sda.library.repository.CategoryDto;
import pl.sda.library.repository.SeriesDto;
import pl.sda.library.repository.UserDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "books")
class BookDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book")
    private Long idBook;
    //private int idUser;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserDto user;

    @ManyToOne
    @JoinColumn(name = "id_bookstore")
    //private int idBookstore;
    private BookstoreDto bookstore;

    @ManyToOne
    @JoinColumn(name = "id_series")
    //private int idSeries;
    private SeriesDto series;

    @ManyToMany()
    @JoinTable(name = "books_authors",
            joinColumns = {@JoinColumn(name ="id_book")},
            inverseJoinColumns = {@JoinColumn(name ="id_author")})
    private Set<AuthorDto> authors = new HashSet<>();

    @ManyToMany()//cascade = {CascadeType.ALL})
    @JoinTable(name = "books_categories",
            joinColumns = {@JoinColumn(name ="id_book")},
            inverseJoinColumns = {@JoinColumn(name ="id_category")})
    private Set<CategoryDto> categories = new HashSet<>();

    private String title;
    private String subtitle;
    private String description;
    private String cover;
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
    private String info;
    @Column(name = "is_read")
    private Boolean isRead;
    private String isbn;
    private int volume;

    Book toModel(){
        Book b=new Book();
        b.setIdBook(getIdBook());
        b.setUserLogin(getUser().getLogin());
        b.setBookstore(getBookstore().getName());
        b.setSeries(getSeries().getTitle());
        b.setAuthors(getAuthorsAsString());
        b.setCategories(getCategoriesAsString());
        b.setTitle(getTitle());
        b.setSubtitle(getSubtitle());
        b.setDescription(getDescription());
        b.setCover(getCover());
        b.setEditionType(getEditionType());
        b.setReadingStatus(getReadingStatus());
        b.setOwnershipStatus(getOwnershipStatus());
        b.setReadFrom(getReadFrom());
        b.setReadTo(getReadTo());
        b.setInfo(getInfo());
        b.setIsRead(getIsRead());
        b.setIsbn(getIsbn());
        b.setVolume(getVolume());
        return b;
    }

     String getAuthorsAsString(){
        String result = "";
        for (AuthorDto authorDto : authors) {
            result += authorDto.getFirstName() + " " + authorDto.getLastName() + ", ";
        }
        int index = result.lastIndexOf(',');
        String substring = result.substring(0, index);
        return substring;
    }
     String getCategoriesAsString(){
        String result = "";
        for (CategoryDto categoryDto: categories){
            result += categoryDto.getName() + ", ";
        }
        int index = result.lastIndexOf(',');
        String substring = result.substring(0, index);
        return substring;
    }
}
