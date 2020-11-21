package pl.sda.library.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.library.LibraryApplication;
import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.model.exception.BookAlreadyExistException;
import pl.sda.library.domain.service.BookService;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
public class BookServiceTest {

    @Autowired
    BookService bookService;

    @Test
    public void should_return_true_book_when_equal_added() {
        //when
        Book book = createBook();
        Book bookAdded = bookService.addBook(book);

        //given
        Book resultBook = bookService.findBook(bookAdded.getIdBook());

        //then
        assertTrue(bookAdded.equals(resultBook));
    }

    @Test
    public void should_return_size_plus_1_when_1_book_added() {
        //when
        Book book = createBook1();
        final long id = bookService.findAllBooks().size();
        bookService.addBook(book);

        //given
        int result = bookService.findAllBooks().size();

        //then
        assertEquals(id + 1, result);
    }

    @Test
    public void should_add_book_to_db() {
        //when
        Book book = createBook2();

        //then
        Long resultId = bookService.addBook(book).getIdBook();

        assertNotEquals(0, resultId);
    }

    @Test
    public void should_throw_BookAlreadyExistException_when_added_book_already_exist() {
        //when
        Book book = createBook3();
        bookService.addBook(book);;

        //then
        assertThrows(BookAlreadyExistException.class, () ->  bookService.addBook(book));
    }


    private Book createBook() {
        Book book = new Book();
        book.setSeries("The Frontires Saga");
        book.setAuthors("Ryk Brown, Jo Nesbo");
        book.setCategories("Fantastyka, Science fiction");
        book.setTitle("Aurora CV-01");
        book.setSubtitle("brak");
        book.setDescription("Wyniszczony zarazą świat, który powstaje z kolan.");
        book.setCover("www.costam.pl");
        book.setBookInSeriesNo(0);
        return book;
    }
    private Book createBook1() {
        Book book = new Book();
        book.setSeries("Odyssey One");
        book.setAuthors("Evan Currie");
        book.setCategories("Fantastyka, Science fiction");
        book.setTitle("Odyssey One");
        book.setSubtitle("Rozgrywka w ciemno");
        book.setDescription("Korzystając z najnowszej technologii, umożliwiającej podróże międzygwiezdne, okręt badawczy „Odyseja” wyrusza w dziewiczy rejs.");
        book.setCover("www.costam2.pl");
        book.setBookInSeriesNo(1);
        return book;
    }
    private Book createBook2() {
        Book book = new Book();
        book.setSeries("");
        book.setAuthors("Michael Connelly");
        book.setCategories("Thriller, Kryminał ,Sensacja");
        book.setTitle("Dwa rodzaje prawdy");
        book.setSubtitle("");
        book.setDescription("Wyrzucony z pracy w policji Los Angeles Harry Bosch pragnie odzyskać dobre imię.");
        book.setCover("www.costam3.pl");
        book.setBookInSeriesNo(0);
        return book;
    }
    private Book createBook3() {
        Book book = new Book();
        book.setSeries("Odyssey One");
        book.setAuthors("Evan Currie");
        book.setCategories("Fantastyka, Science fiction");
        book.setTitle("Odyssey One");
        book.setSubtitle("W samo sedno");
        book.setDescription("Okręt badawczy „Odyseja” powrócił z dziewiczej podróży.");
        book.setCover("www.costam2.pl");
        book.setBookInSeriesNo(2);
        return book;
    }
}

