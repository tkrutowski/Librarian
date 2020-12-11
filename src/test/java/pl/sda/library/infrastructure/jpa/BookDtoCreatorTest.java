package pl.sda.library.infrastructure.jpa;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.library.LibraryApplication;
import pl.sda.library.domain.model.Author;
import pl.sda.library.domain.model.Book;

import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
public class BookDtoCreatorTest {

    @Autowired
    BookDtoCreator bookDtoCreator;

    @Test
    public void should_return_corect_names(){
        //when
        final String author = "Agatha Christie";
        Book book = createBook();
        book.setAuthors(author);

        //given
        BookDto bookDto = bookDtoCreator.fromDomain(book);
        Set<AuthorDto> authors = bookDto.getAuthors();
        Iterator<AuthorDto> iterator = authors.iterator();
        AuthorDto authorDto = iterator.next();

        //then
        assertTrue("Agatha".equals(authorDto.getFirstName()));
        assertTrue("Christie".equals(authorDto.getLastName()));
    }


    @Test
    public void should_return_corect_firstname(){
        //when
        final String author = "  J. R. R.   Tolkien   ";
        Book book = createBook();
        book.setAuthors(author);

        //given
        BookDto bookDto = bookDtoCreator.fromDomain(book);
        Set<AuthorDto> authors = bookDto.getAuthors();
        Iterator<AuthorDto> iterator = authors.iterator();
        AuthorDto authorDto = iterator.next();
        String firstName = authorDto.getFirstName();
        //then
        assertEquals(true, "J. R. R.".equals(firstName));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Agnieszka Lingas-Łoniewska", "Agnieszka Lingas - Łoniewska"})
    public void should_return_corect_lastname(String author){
        //when
        Book book = createBook();
        book.setAuthors(author);

        //given
        BookDto bookDto = bookDtoCreator.fromDomain(book);
        Set<AuthorDto> authors = bookDto.getAuthors();
        Iterator<AuthorDto> iterator = authors.iterator();
        AuthorDto authorDto = iterator.next();

        //then
        assertEquals(true, "Lingas-Łoniewska".equals(authorDto.getLastName()));
    }

    @Test
    public void should_return_corect_author(){
        //when
        final String author = "Anonim";
        Book book = createBook();
        book.setAuthors(author);

        //given
        BookDto bookDto = bookDtoCreator.fromDomain(book);
        Set<AuthorDto> authors = bookDto.getAuthors();
        Iterator<AuthorDto> iterator = authors.iterator();
        AuthorDto authorDto = iterator.next();

        //then
        assertTrue(authorDto.getFirstName().isEmpty());
        assertTrue("Anonim".equals(authorDto.getLastName()));
    }

    private Book createBook() {
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
}