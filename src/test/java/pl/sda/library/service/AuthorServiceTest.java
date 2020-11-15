package pl.sda.library.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.library.LibraryApplication;
import pl.sda.library.domain.model.exception.AuthorDoesNotExistException;
import pl.sda.library.domain.service.AuthorService;
import pl.sda.library.domain.model.exception.ObjectAlreadyExistException;
import pl.sda.library.domain.model.exception.ObjectDoesNotExistException;
import pl.sda.library.domain.model.Author;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @Test
    public void should_return_id_bigger_than_zero_when_author_added() {
        //when
        Author author = new Author(null, "Johnny", "Doo");

        //given
        Long id = authorService.addAuthor(author);

        //then
        assertNotEquals(java.util.Optional.of(0L), id);
    }

    @Test
    public void should_throw_ObjectAlreadyExistException_when_author_already_exist() {
        //when
        Author author = new Author(null, "John", "Doo");
        authorService.addAuthor(author);

        //then
        assertThrows(ObjectAlreadyExistException.class, () -> authorService.addAuthor(author));
    }

    @Test
    public void should_return_changed_firstName_while_edit() {
        //when
        Author author = new Author(null, "John2", "Doo2");
        Long id = authorService.addAuthor(author);
        Author toEdit = authorService.findAuthor(id);
        toEdit.setFirstName("JohnEdit");

        //given
        Author afterEdit = authorService.editAuthor(toEdit);

        //then
        assertEquals("JohnEdit", afterEdit.getFirstName());
    }

    @Test
    public void should_throw_ObjectDoesNotExistException() {
        //when
        Author author = new Author(null, "John2", "Doo2");
        Long id = authorService.addAuthor(author);
        Author toEdit = authorService.findAuthor(id);
        toEdit.setId(0L);
        toEdit.setFirstName("JohnEdit");

        //then
        assertThrows(ObjectDoesNotExistException.class, () -> authorService.editAuthor(toEdit));
    }

    @Test
    public void should_return_changed_lastName_while_edit() {
        //when
        Author author = new Author(null, "John3", "Doo3");
        Long id = authorService.addAuthor(author);
        Author toEdit = authorService.findAuthor(id);
        toEdit.setLastName("DooEdit");

        //given
        Author afterEdit = authorService.editAuthor(toEdit);

        //then
        assertEquals("DooEdit", afterEdit.getLastName());
    }

    @Test
    public void should_return_falsa_when_author_does_not_exist() {
        //when
        Author author = new Author(null, "John3", "Doo3");
        Long id = authorService.addAuthor(author);
        authorService.deleteAuthor(id);

        //given
        boolean result = authorService.deleteAuthor(id);

        //then
        assertFalse(result);
    }

    @Test
    public void should_throw_AuthorDoesNotExistException_while_delete_no_exist_author() {
        //when
        Long id = 0L;

        //then
        assertThrows(AuthorDoesNotExistException.class, () -> authorService.findAuthor(id));
    }

    @Test
    public void should_return_size__plus_3_when_3_authors_added() {
        //when
        final int SIZE = authorService.findAllAuthors().size() + 3;
        authorService.addAuthor(new Author(10L, "John", "Boo"));
        authorService.addAuthor(new Author(20L, "Jack", "Browm"));
        authorService.addAuthor(new Author(30L, "Jim", "Carey"));

        //given
        int result = authorService.findAllAuthors().size();

        //then
        assertEquals(SIZE, result);
    }

    @Test
    public void should_return_size__minus_1_when_one_author_deleted() {
        //when
        final int SIZE = authorService.findAllAuthors().size() - 1;
        authorService.deleteAuthor(3L);

        //given
        int result = authorService.findAllAuthors().size();

        //then
        assertEquals(SIZE, result);
    }
}