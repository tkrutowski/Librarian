package pl.sda.library.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.sda.library.domain.model.User;
import pl.sda.library.domain.service.UserService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    UserService userService;

    @PostMapping
    public Long addUser(@RequestBody User user)  {
        return userService.addUser(user);
    }

    @PutMapping("/{id}")
    public User editUser(@RequestBody User user, @PathVariable Long id)  {
        return userService.editUser(user, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public User findUser(@PathVariable Long id){
        return userService.findUser(id);
    }

    @GetMapping
    public List<User> findAllUsers(){
        return userService.findAllUsers();
    }
}
