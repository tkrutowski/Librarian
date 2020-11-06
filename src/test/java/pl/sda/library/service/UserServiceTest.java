package pl.sda.library.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.library.LibraryApplication;
import pl.sda.library.model.Author;
import pl.sda.library.model.User;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void should_return_true_when_author_added() {
        //when
        User user=new User(null,"login","password","name",false);

        //given
        boolean result = userService.addUser(user);

        //then
        assertTrue(result);
    }

    @Test
    public void should_return_size__plus_2_when_2_users_added() {
        //when
        final int SIZE = userService.getAllUsers().size() + 2;
        userService.addUser(new User(null,"login1","password1","name1",false));
        userService.addUser(new User(null,"login2","password2","name2",true));

        //given
        int result = userService.getAllUsers().size();

        //then
        assertEquals(SIZE, result);
    }
    @Test
    public void should_return_size__minus_1_when_one_user_deleted() {
        //when
        final int SIZE = userService.getAllUsers().size() -1;
        userService.delUser(2L);

        //given
        int result = userService.getAllUsers().size();

        //then
        assertEquals(SIZE, result);
    }
}