package pl.sda.library.infrastructure.empik;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.library.domain.model.Book;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class EmpikProductDto {
    private String bookstore;
    private String authors;
    private String title;
    private String description;
    private String cover;
    private String editionType;
    private String isbn;

    public Book toDomain() {
        return Book.builder()
                //.bookstore(this.bookstore)
                .authors(this.authors)
                .title(this.title)
                .build();
    }

}
