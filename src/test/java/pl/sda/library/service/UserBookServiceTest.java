package pl.sda.library.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.library.LibraryApplication;
import pl.sda.library.domain.model.EditionType;
import pl.sda.library.domain.model.OwnershipStatus;
import pl.sda.library.domain.model.ReadingStatus;
import pl.sda.library.domain.model.UserBook;
import pl.sda.library.domain.model.exception.UserBookAlreadyExistException;
import pl.sda.library.domain.service.UserBookService;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
public class UserBookServiceTest {

    @Autowired
    UserBookService userBookService;

    @Test
    public void should_return_true_when_equal() {
        //when
        UserBook userBook = createUserBook();
        UserBook userBookAdded = userBookService.addUserBook(userBook);

        //given
        UserBook resultBook = userBookService.findUserBook(userBookAdded.getId());

        //then
        assertTrue(userBookAdded.equals(resultBook));
    }

    @Test
    public void should_return_size_plus_1_when_1_book_added() {
        //when
        UserBook userBook = createUserBook1();
        final long id = userBookService.findAllUserBooks().size();
        userBookService.addUserBook(userBook);

        //given
        int result = userBookService.findAllUserBooks().size();

        //then
        assertEquals(id + 1, result);
    }

    @Test
    public void should_add_book_to_db() {
        //when
        UserBook userBook = createUserBook2();

        //then
        Long resultId = userBookService.addUserBook(userBook).getId();

        assertNotEquals(0, resultId);
    }

    @Test
    public void should_throw_UserBookAlreadyExistException_when_added_userBook_already_exist() {
        //when
        UserBook userBook = createUserBook3();
        userBookService.addUserBook(userBook);
        ;

        //then
        assertThrows(UserBookAlreadyExistException.class, () -> userBookService.addUserBook(userBook));
    }


    private UserBook createUserBook() {
        UserBook userBook = new UserBook();
        userBook.setIdBook(1L);
        userBook.setIdUser(1L);
        userBook.setBookstore("Empik");
        userBook.setEditionType(EditionType.AUDIOBOOK);
        userBook.setReadingStatus(ReadingStatus.NOT_READ);
        userBook.setOwnershipStatus(OwnershipStatus.READ_ONLY);
//        userBook.setReadFrom();
//        userBook.setReadTo();
        userBook.setInfo("Jakies info");
        return userBook;
    }

    private UserBook createUserBook1() {
        UserBook userBook = new UserBook();
        userBook.setIdBook(1L);
        userBook.setIdUser(1L);
        userBook.setBookstore("Empik");
        userBook.setEditionType(EditionType.EBOOK);
        userBook.setReadingStatus(ReadingStatus.NOT_READ);
        userBook.setOwnershipStatus(OwnershipStatus.READ_ONLY);
//        userBook.setReadFrom();
//        userBook.setReadTo();
        userBook.setInfo("Jakies info");
        return userBook;
    }

    private UserBook createUserBook2() {
        UserBook userBook = new UserBook();
        userBook.setIdBook(2L);
        userBook.setIdUser(1L);
        userBook.setBookstore("Empik");
        userBook.setEditionType(EditionType.EBOOK);
        userBook.setReadingStatus(ReadingStatus.NOT_READ);
        userBook.setOwnershipStatus(OwnershipStatus.READ_ONLY);
//        userBook.setReadFrom();
//        userBook.setReadTo();
        userBook.setInfo("Jakies info");
        return userBook;
    }

    private UserBook createUserBook3() {
        UserBook userBook = new UserBook();
        userBook.setIdBook(2L);
        userBook.setIdUser(1L);
        userBook.setBookstore("Empik");
        userBook.setEditionType(EditionType.BOOK);
        userBook.setReadingStatus(ReadingStatus.NOT_READ);
        userBook.setOwnershipStatus(OwnershipStatus.READ_ONLY);
//        userBook.setReadFrom();
//        userBook.setReadTo();
        userBook.setInfo("Jakies info");
        return userBook;
    }
}