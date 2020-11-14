package pl.sda.library.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.library.LibraryApplication;
import pl.sda.library.domain.model.*;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
public class DtoFactoryTest {

    @Autowired
    DtoFactory dtoFactory;

    @Test
    public void should_return_equal_bookDto_when_book_given() {
        //when
        Book book=createBook();

        int i=0;
        //given
        BookDto bookDto = dtoFactory.createBookDto(book);

        //then
        assertEquals(book.getIdBook(),bookDto.getIdBook());
        assertEquals(book.getUserLogin(),bookDto.getUser().getLogin());
        assertEquals(book.getBookstore(),bookDto.getBookstore().getName());
        assertEquals(book.getSeries(),bookDto.getSeries().getTitle());
        assertEquals(book.getAuthors(),bookDto.getAuthorsAsString());
        assertEquals(book.getCategories(),bookDto.getCategoriesAsString());
        assertEquals(book.getTitle(),bookDto.getTitle());
        assertEquals(book.getSubtitle(),bookDto.getSubtitle());
        assertEquals(book.getDescription(),bookDto.getDescription());
        assertEquals(book.getCover(),bookDto.getCover());
        assertEquals(book.getEditionType(),bookDto.getEditionType());
        assertEquals(book.getReadingStatus(),bookDto.getReadingStatus());
        assertEquals(book.getOwnershipStatus(),bookDto.getOwnershipStatus());
        assertEquals(book.getReadFrom(),bookDto.getReadFrom());
        assertEquals(book.getReadTo(),bookDto.getReadTo());
        assertEquals(book.getInfo(),bookDto.getInfo());
        assertEquals(book.getIsRead(),bookDto.getIsRead());
        assertEquals(book.getIsbn(),bookDto.getIsbn());
        assertEquals(book.getVolume(),bookDto.getVolume());
    }

    @Test
    public void should_return_equal_userDto_when_user_given() {
        //when
        User user=createUser();

        int i=0;
        //given
        UserDto userDto=dtoFactory.createUserDto(user);

        //then
        assertEquals(user.getIdUser(),userDto.getIdUser());
        assertEquals(user.getLogin(),userDto.getLogin());
        assertEquals(user.getName(),userDto.getName());
        assertEquals(user.getPassword(),userDto.getPassword());
        assertEquals(user.getIsAdmin(),userDto.getIsAdmin());
    }

    private Book createBook(){
            Book book=new Book();
            book.setIdBook(1L);
            book.setUserLogin("test@gmail.com");
            book.setBookstore("Empik");
            book.setSeries("The Frontires Saga");
            book.setAuthors("Lee Child, Ryk Brown");
            book.setCategories("Fantastyka / Science fiction");
            book.setTitle("Aurora CV-01");
            book.setSubtitle("test");
            book.setDescription("Wyniszczony zarazą świat, który powstaje z kolan.");
            book.setCover("www.costam.pl");
            book.setEditionType(EditionType.AUDIOBOOK);
            book.setReadingStatus(ReadingStatus.NOT_READ);
            book.setOwnershipStatus(OwnershipStatus.READ_ONLY);
            book.setReadFrom(LocalDate.of(2020, 1, 8));
            book.setReadTo(LocalDate.of(2020, 2, 18));
            book.setInfo("test");
            book.setIsRead(false);
            book.setIsbn("123-456");
            book.setVolume(1);
            return book;
    }
    private User createUser(){
        User user = new User();
        user.setIdUser(2L);
        user.setIsAdmin(false);
        user.setLogin("admin");
        user.setName("tomek");
        user.setPassword("s3cret");
        return user;
    }
}