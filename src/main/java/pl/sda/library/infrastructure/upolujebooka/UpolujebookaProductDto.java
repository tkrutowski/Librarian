package pl.sda.library.infrastructure.upolujebooka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.model.EditionType;

@Data
@Builder (toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UpolujebookaProductDto {
    private String bookstore;
    private String authors;
    private String title;
    private String description;
    private String cover;
    private String editionType;
    private String isbn;

    public Book toDomain(){
        return Book.builder()
                .bookstore(this.bookstore)
                .authors(this.authors)
                .title(this.title)
                .description(this.description)
                .cover(this.cover)
                .editionType(convertEditionType(this.editionType))
                .isbn(this.isbn)
                .build();
    }

    private EditionType convertEditionType(String editionType) {
        EditionType returnedValue;
        //    BOOK, EBOOK, AUDIOBOOK
        if(editionType == "BOOK") {
            returnedValue = EditionType.BOOK;
        }else if (editionType == "EBOOK"){
            returnedValue = EditionType.EBOOK;
        }
        else {
            returnedValue = EditionType.AUDIOBOOK;
        }
        return returnedValue;
    }

    ;



    /*
    * private String bookstore; //nazwa księgarni np. Empik
    * private String authors; //autorzy: imie nazwisko            !!!!!!!!!!!
    * private String title; //tytuł                                 !!!!!!!!!!
    * private String description; //krótki opis książki           !!!!!!!!!!
    * private String cover; //link do okładki                     !!!!!!!!!
    * private EditionType editionType; //typ: EBOOK, AUDIOBOOK lub BOOK   !!!!!!!!!!!!
    * private String isbn;  //
    private ReadingStatus readingStatus;
    private Long idBook;
    private String userLogin;
    private String series; //cykl książek np 'Jack Reacher'   !!!!!!!!!!
    private String categories; //kategorie np. Thriller, kryminał  !!!!!!!!!
    private String subtitle;//podtytuł jeżęli jest              !!!!!!!!!!
    private OwnershipStatus ownershipStatus;
    private LocalDate readFrom; //zaczęto czytać
    private LocalDate readTo; //skończono czytać
    private String info; //jakieś swoje zapiski
    private boolean isRead;//czy przeczytana

     */
}
