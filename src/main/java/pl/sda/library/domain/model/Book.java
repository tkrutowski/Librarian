package pl.sda.library.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book {
    private Long id;
    private String series;
    private String authors;
    private String categories;
    private String title;
    private String subtitle;
    private String description;
    private String cover;
    private int bookInSeriesNo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getBookInSeriesNo() == book.getBookInSeriesNo() &&
                Objects.equals(getSeries(), book.getSeries()) &&
                getAuthors().equals(book.getAuthors()) &&
                getTitle().equals(book.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSeries(), getAuthors(), getTitle(), getBookInSeriesNo());
    }
}

