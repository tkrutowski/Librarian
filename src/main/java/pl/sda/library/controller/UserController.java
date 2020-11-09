package pl.sda.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.sda.library.model.User;
import pl.sda.library.service.UserService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/add")
    public Long addUser(@RequestBody User user)  {
        return userService.addUser(user);
    }

    @PutMapping("/edit")
    public User editUser(@RequestBody User user)  {
        return userService.editUser(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @DeleteMapping("/{id}")
    public void delUser(@PathVariable Long id){
        userService.delUser(id);
    }
}
