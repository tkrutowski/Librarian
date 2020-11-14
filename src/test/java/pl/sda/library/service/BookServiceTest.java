package pl.sda.library.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.library.LibraryApplication;
import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.model.EditionType;
import pl.sda.library.domain.model.OwnershipStatus;
import pl.sda.library.domain.model.ReadingStatus;
import pl.sda.library.domain.service.BookService;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
public class BookServiceTest {

    @Autowired
    BookService bookService;
    @Test
    public void should_return_book_with_correct_id() {
        //when
        final long id=1;
        Book book = createBook();
        bookService.addBook(book);

        //given
        Book resultBook=bookService.getBook(id);

        //then
        assertEquals(id,resultBook.getIdBook());
    }

    @Test
    public void getAllBooks() {
    }

    @Test
    public void should_add_book_to_db() {
        //when
        Book book = createBook();

        //then
        boolean result = bookService.addBook(book);

        assertTrue(result);

    }

    //TODO - dodanie książki z wszystkimi danymi
    //TODO - edycja

    private Book createBook(){
        Book book=new Book();
        book.setIsRead(false);
        book.setOwnershipStatus(OwnershipStatus.READ_ONLY);
        book.setEditionType(EditionType.AUDIOBOOK);
        book.setCover("www.costam.pl");
        book.setDescription("Wyniszczony zarazą świat, który powstaje z kolan.");
        book.setTitle("Aurora CV-01");
        book.setSeries("The Frontires Saga");
        book.setCategories("Fantastyka, Science fiction");
        book.setAuthors("Ryk Brown, Lucky Luck");
        book.setBookstore("Empik");
        book.setReadingStatus(ReadingStatus.NOT_READ);
        book.setUserLogin("test@gmail.com");
        book.setIsbn("987-456");
        book.setSubtitle("brak");
        return book;
    }
}

