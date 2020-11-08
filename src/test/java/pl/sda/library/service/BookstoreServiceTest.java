package pl.sda.library.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.library.LibraryApplication;
import pl.sda.library.model.Author;
import pl.sda.library.model.Bookstore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
public class BookstoreServiceTest {

    @Autowired
    BookstoreService bookstoreService;

    @Test
    public void should_return_true_when_bookstore_added() {
        //when
        Bookstore bookstore = new Bookstore(null, "Helion","www.helion.pl");

        //given
        boolean result = bookstoreService.addBookstore(bookstore);

        //then
        assertTrue(result);
    }

    @Test
    public void should_return_size__plus_3_when_3_authors_added() {
        //when
        final int SIZE = bookstoreService.getAllBookstores().size() + 3;
        bookstoreService.addBookstore(new Bookstore(10L,"Empik","www.empik.com"));
        bookstoreService.addBookstore(new Bookstore(20L,"PWN","www.pwn.pl"));
        bookstoreService.addBookstore(new Bookstore(30L,"Legimi","www.legimi.pl"));

        //given
        int result = bookstoreService.getAllBookstores().size();

        //then
        assertEquals(SIZE, result);
    }
    @Test
    public void should_return_size__minus_1_when_one_author_deleted() {
        //when
        final int SIZE = bookstoreService.getAllBookstores().size() -1;
        bookstoreService.delBookstore(3L);

        //given
        int result = bookstoreService.getAllBookstores().size();

        //then
        assertEquals(SIZE, result);
    }

}