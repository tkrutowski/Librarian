package pl.sda.library.infrastructure.upolujebooka;

import org.junit.jupiter.api.Test;
import pl.sda.library.domain.model.Book;

import java.net.MalformedURLException;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UpolujebookaScrapperTest {

    @Test
    void ShouldFindOnlyTwoAuthors() throws MalformedURLException {

        //given
//        String bookUrl = "https://upolujebooka.pl/oferta,6519,dobry_omen.html";
        String bookUrl = "https://upolujebooka.pl/oferta,42943,w_samo_sedno.html";
        //String phrase = "Neil Gaiman";
        Book book= new Book();
        //when
        book = UpolujebookaScrapper.findBookFromUrl(bookUrl);
        String atuhors[] = book.getAuthors().split(",");
        //then
        assertEquals(2,atuhors.length);
    }

}