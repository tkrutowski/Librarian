package pl.sda.library.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.library.LibraryApplication;
import pl.sda.library.model.Category;
import pl.sda.library.service.exceptions.ObjectAlreadyExistException;
import pl.sda.library.service.exceptions.ObjectDoesNotExistException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void should_return_id_bigger_than_zero_when_category_added() {
        //when
        Category category = new Category(null,"Komedia");

        //given
        Long result = categoryService.addCategory(category);

        //then
        assertNotEquals(java.util.Optional.of(0),result);
    }
    @Test
    public void should_throw_ObjectAlreadyExistException_when_category_already_exist()   {
        //when
        Category category = new Category(null,"Dramat");
        categoryService.addCategory(category);

        //then
        assertThrows(ObjectAlreadyExistException.class, () -> categoryService.addCategory(category));
    }
    @Test
    public void should_return_changed_Name_while_edit()  {
        //when
        Category category = new Category(null,"Fantasy");
        Long id =  categoryService.addCategory(category);
        Category toEdit = categoryService.getCategory(id);
        toEdit.setName("Fantazja");

        //given
        Category afterEdit = categoryService.editCategory(toEdit);

        //then
        assertEquals("Fantazja", afterEdit.getName());
    }

    @Test
    public void should_throw_ObjectDoesNotExistException()   {
        //when
        Category category = new Category(null,"SF");
        Long id =  categoryService.addCategory(category);
        Category toEdit = categoryService.getCategory(id);
        toEdit.setIdCategory(0L);
        toEdit.setName("Science fiction");

        //then
        assertThrows(ObjectDoesNotExistException.class, () -> categoryService.editCategory(toEdit));
    }

    @Test
    public void should_return_size__plus_2_when_2_categories_added() {
        //when
        final int SIZE = categoryService.getAllCategories().size() + 2;
        categoryService.addCategory(new Category(null,"Horror"));
        categoryService.addCategory(new Category(null,"Historyczny"));

        //given
        int result = categoryService.getAllCategories().size();

        //then
        assertEquals(SIZE, result);
    }

    @Test
    public void should_return_size__minus_1_when_one_category_deleted() {
        //when
        final int SIZE = categoryService.getAllCategories().size() -1;
        categoryService.delCategory(3L);

        //given
        int result = categoryService.getAllCategories().size();

        //then
        assertEquals(SIZE, result);
    }

}