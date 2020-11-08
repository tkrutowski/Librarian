package pl.sda.library.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.library.LibraryApplication;
import pl.sda.library.model.Author;
import pl.sda.library.model.Category;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void should_return_true_when_category_added() {
        //when
        Category category = new Category(null,"Komedia");

        //given
        boolean result = categoryService.addCategory(category);

        //then
        assertTrue(result);
    }

    @Test
    public void should_return_size__plus_2_when_2_categories_added() {
        //when
        final int SIZE = categoryService.getAllCategories().size() + 2;
        categoryService.addCategory(new Category(null,"Fantasy"));
        categoryService.addCategory(new Category(null,"SF"));

        //given
        int result = categoryService.getAllCategories().size();

        //then
        assertEquals(SIZE, result);
    }
    @Test
    public void should_return_size__minus_1_when_one_author_deleted() {
        //when
        final int SIZE = categoryService.getAllCategories().size() -1;
        categoryService.delCategory(3L);

        //given
        int result = categoryService.getAllCategories().size();

        //then
        assertEquals(SIZE, result);
    }

}