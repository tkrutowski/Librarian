package pl.sda.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.library.domain.model.Role;
import pl.sda.library.domain.model.User;
import pl.sda.library.domain.service.UserService;

import java.util.Arrays;
import java.util.List;
@AllArgsConstructor
@Controller
@RequestMapping("/users")
public class WebUserController {
    UserService userService;

    @GetMapping
    public String findAllUsers(Model model){
        List<Role> roles = Arrays.asList(Role.values());
        model.addAttribute(new User());
        model.addAttribute("roles", roles);
        model.addAttribute("usersList",userService.findAllUsers());
        return "users";

    }

    @PostMapping
    public String addUser(User user)  {
        userService.addUser(user);
        return "redirect:/users";
    }

}
