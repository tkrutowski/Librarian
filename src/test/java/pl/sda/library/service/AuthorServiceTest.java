package pl.sda.library.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.library.LibraryApplication;
import pl.sda.library.model.Author;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @Test
    public void should_return_true_when_author_added() {
        //when
        Author author=new Author(10L,"John","Doo");

        //given
        boolean result = authorService.addAuthor(author);

        //then
        assertTrue(result);
    }

    @Test
    public void should_return_size__plus_3_when_3_authors_added() {
        //when
        final int SIZE = authorService.getAllAuthors().size() + 3;
        authorService.addAuthor(new Author(10L,"John","Doo"));
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