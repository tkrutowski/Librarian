package pl.sda.library.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.library.LibraryApplication;
import pl.sda.library.domain.model.Role;
import pl.sda.library.domain.model.User;
import pl.sda.library.domain.service.UserService;
import pl.sda.library.domain.model.exception.ObjectAlreadyExistException;
import pl.sda.library.domain.model.exception.ObjectDoesNotExistException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void should_return_id_bigger_then_when_user_added() {
        //when
        User user = new User(null, "admin10", "password", "name10", Role.ADMIN);

        //given
        Long id = userService.addUser(user);

        //then
        assertNotEquals(java.util.Optional.of(0L), id);
    }

    @Test
    public void should_throw_ObjectAlreadyExistException_when_author_already_exist() {
        //when
        User user = new User(null, "user", "password1", "name1", Role.USER);
        userService.addUser(user);

        //then
        assertThrows(ObjectAlreadyExistException.class, () -> userService.addUser(user));
    }

    @Test
    public void should_return_changed_password_while_edit() {
        //when
        User user = new User(null, "login2", "password2", "name2", Role.USER);
        Long id = userService.addUser(user);
        User toEdit = userService.findUser(id);
        toEdit.setPassword("password2_new");

        //given
        User afterEdit = userService.editUser(toEdit, id);

        //then
        assertEquals("password2_new", afterEdit.getPassword());
    }

    @Test
    public void should_return_changed_name_while_edit() {
        //when
        User user = new User(null, "login3", "password3", "name3", Role.USER);
        Long id = userService.addUser(user);
        User toEdit = userService.findUser(id);
        toEdit.setName("name3_new");

        //given
        User afterEdit = userService.editUser(toEdit, id);

        //then
        assertEquals("name3_new", afterEdit.getName());
    }

    @Test
    public void should_throw_UserDoesNotExistException() {
        //when
        User user = new User(null, "login4", "password4", "name4", Role.USER);
        Long id = userService.addUser(user);
        User toEdit = userService.findUser(id);
        toEdit.setId(0L);
        toEdit.setName("name3_new");

        //then
        assertThrows(ObjectDoesNotExistException.class, () -> userService.editUser(toEdit, 0L));
    }

    @Test
    public void should_return_size__plus_2_when_2_users_added() {
        //when
        final int SIZE = userService.findAllUsers().size() + 2;
        userService.addUser(new User(null, "login5", "password5", "name5", Role.ADMIN));
        userService.addUser(new User(null, "login6", "password6", "name6", Role.USER));

        //given
        int result = userService.findAllUsers().size();

        //then
        assertEquals(SIZE, result);
    }

    @Test
    public void should_return_size__minus_1_when_one_user_deleted() {
        //when
        final int SIZE = userService.findAllUsers().size() - 1;
        userService.deleteUser(2L);

        //given
        int result = userService.findAllUsers().size();

        //then
        assertEquals(SIZE, result);
    }
}