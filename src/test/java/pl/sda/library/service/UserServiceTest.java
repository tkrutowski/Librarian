package pl.sda.library.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.library.LibraryApplication;
import pl.sda.library.domain.model.User;
import pl.sda.library.domain.service.UserService;
import pl.sda.library.domain.model.exception.ObjectAlreadyExistException;
import pl.sda.library.domain.model.exception.ObjectDoesNotExistException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void should_return_id_bigger_then_when_user_added() {
        //when
        User user = new User(null, "login", "password", "name", false);

        //given
        Long id = userService.addUser(user);

        //then
        assertNotEquals(java.util.Optional.of(0L), id);
    }

    @Test
    public void should_throw_ObjectAlreadyExistException_when_author_already_exist() {
        //when
        User user = new User(null, "login1", "password1", "name1", false);
        userService.addUser(user);

        //then
        assertThrows(ObjectAlreadyExistException.class, () -> userService.addUser(user));
    }

    @Test
    public void should_return_changed_password_while_edit() {
        //when
        User user = new User(null, "login2", "password2", "name2", false);
        Long id = userService.addUser(user);
        User toEdit = userService.getUser(id);
        toEdit.setPassword("password2_new");

        //given
        User afterEdit = userService.editUser(toEdit);

        //then
        assertEquals("password2_new", afterEdit.getPassword());
    }

    @Test
    public void should_return_changed_name_while_edit() {
        //when
        User user = new User(null, "login3", "password3", "name3", false);
        Long id = userService.addUser(user);
        User toEdit = userService.getUser(id);
        toEdit.setName("name3_new");

        //given
        User afterEdit = userService.editUser(toEdit);

        //then
        assertEquals("name3_new", afterEdit.getName());
    }

    @Test
    public void should_throw_ObjectDoesNotExistException() {
        //when
        User user = new User(null, "login4", "password4", "name4", false);
        Long id = userService.addUser(user);
        User toEdit = userService.getUser(id);
        toEdit.setIdUser(0L);
        toEdit.setName("name3_new");

        //then
        assertThrows(ObjectDoesNotExistException.class, () -> userService.editUser(toEdit));
    }

    @Test
    public void should_return_size__plus_2_when_2_users_added() {
        //when
        final int SIZE = userService.getAllUsers().size() + 2;
        userService.addUser(new User(null, "login5", "password5", "name5", false));
        userService.addUser(new User(null, "login6", "password6", "name6", true));

        //given
        int result = userService.getAllUsers().size();

        //then
        assertEquals(SIZE, result);
    }

    @Test
    public void should_return_size__minus_1_when_one_user_deleted() {
        //when
        final int SIZE = userService.getAllUsers().size() - 1;
        userService.delUser(2L);

        //given
        int result = userService.getAllUsers().size();

        //then
        assertEquals(SIZE, result);
    }
}