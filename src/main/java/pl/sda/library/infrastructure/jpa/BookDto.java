package pl.sda.library.infrastructure.jpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sda.library.domain.model.Book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_series")
    private SeriesDto series;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "books_authors",
            joinColumns = {@JoinColumn(name = "id_book")},
            inverseJoinColumns = {@JoinColumn(name = "id_author")})
    private Set<AuthorDto> authors = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "books_categories",
            joinColumns = {@JoinColumn(name = "id_book")},
            inverseJoinColumns = {@JoinColumn(name = "id_category")})
    private Set<CategoryDto> categories = new HashSet<>();

    private String title;
    private String subtitle;
    private String description;
    private String cover;
    private int bookInSeriesNo;

    Book toDomain() {
        Book book = new Book();
        book.setIdBook(getId());
        book.setSeries(series == null ? "" : getSeries().getTitle());
        book.setAuthors(getAuthorsAsString());
        book.setCategories(getCategoriesAsString());
        book.setTitle(getTitle());
        book.setSubtitle(getSubtitle());
        book.setDescription(getDescription());
        book.setCover(getCover());
        book.setBookInSeriesNo(getBookInSeriesNo());
        return book;
    }

    private String getAuthorsAsString() {
        String result = "";
        for (AuthorDto authorDto : authors) {
            result += authorDto.getFirstName() + " " + authorDto.getLastName() + ", ";
        }
        int index = result.lastIndexOf(',');
        String substring = result.substring(0, index);
        return substring;
    }

    private String getCategoriesAsString() {
        String result = "";
        for (CategoryDto categoryDto : categories) {
            result += categoryDto.getName() + ", ";
        }
        int index = result.lastIndexOf(',');
        String substring = result.substring(0, index);
        return substring;
    }
}
