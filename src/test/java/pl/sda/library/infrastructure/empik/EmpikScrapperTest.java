package pl.sda.library.infrastructure.empik;

import org.junit.jupiter.api.Test;
import pl.sda.library.infrastructure.upolujebooka.UpolujebookaAdapter;
import pl.sda.library.model.Book;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmpikScrapperTest {

    @Test
    void ShouldFindFourElements(){
        //given
        //String phrase = "Dobry";
        //String phrase = "omen";
        String phrase = "Dobry Omen";
        //String phrase = "Neil Gaiman";

        EmpikAdapter empikAdapter = new EmpikAdapter();
        //when
        List<Book> books = empikAdapter.findByPhrase(phrase);
        //then
        assertEquals(4, books.size());
    }
}