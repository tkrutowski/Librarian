package pl.sda.library.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.sda.library.domain.model.User;
import pl.sda.library.domain.service.UserService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }

    @PostMapping("/add")
    public Long addUser(@RequestBody User user)  {
        return userService.addUser(user);
    }

    @PutMapping("/edit")
    public User editUser(@RequestBody User user, @PathVariable Long id)  {
        return userService.editUser(user, id);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.findUser(id);
    }

    @DeleteMapping("/{id}")
    public void delUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
