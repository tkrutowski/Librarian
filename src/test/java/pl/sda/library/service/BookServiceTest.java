package pl.sda.library.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public void should_return_true_book_when_equal_added() {
        //when
        Book book = createBook();
        Book bookAdded = bookService.addBook(book);

        //given
        Book resultBook = bookService.findBook(bookAdded.getId());

        //then
        assertTrue(bookAdded.equals(resultBook));
    }

    @Test
    @Transactional
    public void should_return_size_plus_1_when_1_book_added() {
        //when
        Book book = createBook();
        final long id = bookService.findAllBooks().size();
        bookService.addBook(book);

        //given
        int result = bookService.findAllBooks().size();

        //then
        assertEquals(id + 1, result);
    }

    @Test
    @Transactional
    public void should_add_book_to_db() {
        //when
        Book book = createBook();

        //then
        Long resultId = bookService.addBook(book).getId();

        assertNotEquals(0, resultId);
    }

    @Test
    @Transactional
    public void should_throw_BookAlreadyExistException_when_added_book_already_exist() {
        //when
        Book book = createBook();
        bookService.addBook(book);

        //then
        assertThrows(BookAlreadyExistException.class, () ->  bookService.addBook(book));
    }


    private Book createBook() {
        Book book = new Book();
        book.setSeries("The Frontires Saga");
        book.setAuthors("Ryk Brown, Jo Nesbo");
        book.setCategories("Fantastyka, Science fiction");
        book.setTitle("Aurora CV-01");
        book.setDescription("Wyniszczony zarazą świat, który powstaje z kolan.");
        book.setCover("www.costam.pl");
        book.setBookInSeriesNo(0);
        return book;
    }
}

