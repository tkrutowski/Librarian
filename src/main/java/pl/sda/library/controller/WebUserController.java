package pl.sda.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.library.domain.model.Role;
import pl.sda.library.domain.model.User;
import pl.sda.library.domain.model.exception.UserAlreadyExistException;
import pl.sda.library.domain.service.UserService;

import javax.servlet.http.HttpServletRequest;
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



    @GetMapping("/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        //UserDto userDto = new UserDto();
        User user=new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public ModelAndView registerUserAccount (@ModelAttribute("user")  User user, HttpServletRequest request, Errors errors) {
            ModelAndView mav = new ModelAndView();

        try {
            Long registered = userService.addUser(user);
        } catch (UserAlreadyExistException uaeEx) {
            mav.addObject("message", "An account for that username/email already exists.");
            return mav;
        }

        return new ModelAndView("successRegister", "user", user);
    }
}
