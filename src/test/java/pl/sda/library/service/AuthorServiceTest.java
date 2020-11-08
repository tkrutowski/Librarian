package pl.sda.library.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.library.LibraryApplication;
import pl.sda.library.service.exceptions.ObjectAlreadyExistException;
import pl.sda.library.service.exceptions.ObjectDoesNotExistException;
import pl.sda.library.model.Author;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @Test
    public void should_return_id_bigger_than_zero_when_author_added()   {
        //when
        Author author=new Author(null,"Johnny","Doo");

        //given
        Long id = authorService.addAuthor(author);

        //then
        assertNotEquals(java.util.Optional.of(0L),id);
    }
    @Test
    public void should_throw_ObjectAlreadyExistException_when_author_already_exist()   {
        //when
        Author author=new Author(null,"John","Doo");
        authorService.addAuthor(author);

        //then
        assertThrows(ObjectAlreadyExistException.class, () ->authorService.addAuthor(author));
    }

    @Test
    public void should_return_changed_firstName_while_edit()  {
        //when
        Author author=new Author(null,"John2","Doo2");
        Long id = authorService.addAuthor(author);
        Author toEdit = authorService.getAuthor(id);
        toEdit.setFirstName("JohnEdit");

        //given
        Author afterEdit = authorService.editAuthor(toEdit);

        //then
        assertEquals("JohnEdit", afterEdit.getFirstName());
    }
    @Test
    public void should_throw_ObjectDoesNotExistException()   {
        //when
        Author author=new Author(null,"John2","Doo2");
        Long id = authorService.addAuthor(author);
        Author toEdit = authorService.getAuthor(id);
        toEdit.setId(0L);
        toEdit.setFirstName("JohnEdit");

        //then
        assertThrows(ObjectDoesNotExistException.class, () -> authorService.editAuthor(toEdit));
    }

    @Test
    public void should_return_changed_lastName_while_edit()  {
        //when
        Author author=new Author(null,"John3","Doo3");
        Long id = authorService.addAuthor(author);
        Author toEdit = authorService.getAuthor(id);
        toEdit.setLastName("DooEdit");

        //given
        Author afterEdit = authorService.editAuthor(toEdit);

        //then
        assertEquals("DooEdit", afterEdit.getLastName());
    }

    @Test
    public void should_return_size__plus_3_when_3_authors_added()   {
        //when
        final int SIZE = authorService.getAllAuthors().size() + 3;
        authorService.addAuthor(new Author(10L,"John","Boo"));
        authorService.addAuthor(new Author(20L,"Jack","Browm"));
        authorService.addAuthor(new Author(30L,"Jim","Carey"));

        //given
        int result = authorService.getAllAuthors().size();

        //then
        assertEquals(SIZE, result);
    }
    @Test
    public void should_return_size__minus_1_when_one_author_deleted() {
        //when
        final int SIZE = authorService.getAllAuthors().size() -1;
        authorService.delAuthor(3L);

        //given
        int result = authorService.getAllAuthors().size();

        //then
        assertEquals(SIZE, result);
    }
}