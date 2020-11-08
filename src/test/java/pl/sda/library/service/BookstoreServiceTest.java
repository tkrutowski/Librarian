package pl.sda.library.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.library.LibraryApplication;
import pl.sda.library.model.Bookstore;
import pl.sda.library.service.exceptions.ObjectAlreadyExistException;
import pl.sda.library.service.exceptions.ObjectDoesNotExistException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
public class BookstoreServiceTest {

    @Autowired
    BookstoreService bookstoreService;

    @Test
    public void should_return_true_when_bookstore_added() {
        //when
        Bookstore bookstore = new Bookstore(null, "Helion","www.helion.pl");
        Long unexpected = 0L;
        //given
        Long result = bookstoreService.addBookstore(bookstore);

        //then
        assertNotEquals(unexpected,result);
    }

    @Test
    public void should_return_size__plus_3_when_3_authors_added() {
        //when
        final int SIZE = bookstoreService.getAllBookstores().size() + 3;
        bookstoreService.addBookstore(new Bookstore(10L,"Empik1","www.empik.com"));
        bookstoreService.addBookstore(new Bookstore(20L,"PWN1","www.pwn.pl"));
        bookstoreService.addBookstore(new Bookstore(30L,"Legimi1","www.legimi.pl"));

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
    @Test
    public void should_throw_ObjectAlreadyExistException_when_bookstore_already_exist()   {
        //when
        Bookstore  bookstore = new Bookstore(10L,"Gandalf","www.gandalf.com");
       bookstoreService.addBookstore(bookstore);

        //then
        assertThrows(ObjectAlreadyExistException.class, () -> bookstoreService.addBookstore(bookstore));
    }

    @Test
    public void should_return_changed_name_while_edit()  {
        //when
        Bookstore  bookstore = new Bookstore(null,"Arsenał2","www.arsenal.com");
        Long id = bookstoreService.addBookstore(bookstore);
        Bookstore toEdit = bookstoreService.getBookstore(id);
        toEdit.setName("Arsenał2");

        //given
        Bookstore afterEdit = bookstoreService.editBookstore(toEdit);

        //then
        assertEquals("Arsenał2", afterEdit.getName());
    }
    @Test
    public void should_throw_ObjectDoesNotExistException()   {
        //when
        Bookstore  bookstore = new Bookstore(null,"Arsenał","www.arsenal.com");
        Long id = bookstoreService.addBookstore(bookstore);
        Bookstore toEdit = bookstoreService.getBookstore(id);

        toEdit.setIdBookstore(0L);
        toEdit.setName("Arsenał2");

        //then
        assertThrows(ObjectDoesNotExistException.class, () -> bookstoreService.editBookstore(toEdit));
    }

    @Test
    public void should_return_changed_www_while_edit()  {
        //when
        Bookstore  bookstore = new Bookstore(10L,"Gandalf","www.gandalf.com");
        Long id = bookstoreService.addBookstore(bookstore);
        Bookstore toEdit = bookstoreService.getBookstore(id);
        toEdit.setWww("www.gandalf.com.pl");


        //given
        Bookstore afterEdit = bookstoreService.editBookstore(toEdit);

        //then
        assertEquals("www.gandalf.com.pl", afterEdit.getWww());
    }
}