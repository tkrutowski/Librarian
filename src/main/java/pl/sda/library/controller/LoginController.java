package pl.sda.library.controller;


import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.library.domain.model.Role;
import pl.sda.library.domain.model.User;
import pl.sda.library.domain.service.UserService;
import pl.sda.library.domain.service.UserValidator;


@Controller
@AllArgsConstructor
@RequestMapping("/")
class LoginController {

    private UserService userService;
    private UserValidator userValidator;
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String getStart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal().equals("anonymousUser")) {
            return "redirect:/login";
        }
        return "redirect:/home";
    }

    @GetMapping("/login")
    public ModelAndView login() {
        int i = 0;
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.addObject("infoBar", "LOGOWANIE");
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @GetMapping("/login-error")
    public ModelAndView loginError() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.addObject("loginError", true);
        modelAndView.addObject("infoBar", "LOGOWANIE NIEUDANE");
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/register")
    ModelAndView registrationForm() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @GetMapping("/error403")
    public String error403() {
        return "403";
    }

    @PostMapping("/register")
    public String registration(@ModelAttribute("user") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userForm.setPassword(passwordEncoder.encode(userForm.getPasswordConfirm()));
        userForm.setRole(Role.USER);
        userService.addUser(userForm);

        return "redirect:/";
    }
}