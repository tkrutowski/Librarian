package pl.sda.library.controller;


import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.library.domain.model.User;
import pl.sda.library.domain.service.UserService;


@Controller
@AllArgsConstructor
@RequestMapping("/")
class LoginController {

    private UserService userService;

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

   /* @PostMapping("/logout")
    public ModelAndView logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       // authentication.setAuthenticated(false);
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.addObject("infoBar", "WYLOGOWANO");
        modelAndView.setViewName("login");
        return modelAndView;
    }*/

    @RequestMapping(value = {"/login-error"}, method = RequestMethod.GET)
    public ModelAndView loginError() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.addObject("loginError", true);
        modelAndView.addObject("infoBar", "LOGOWANIE NIEUDANE");
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    ModelAndView registration() {
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


//    @RequestMapping(value = "/registration", method = RequestMethod.POST)
//    ModelAndView createNewUser(@Valid UserDto userDto, BindingResult bindingResult) {
//        ModelAndView modelAndView = new ModelAndView();
//        boolean userExists = userService.isUsernameTaken(userDto.getUsername());
//        if (userExists) {
//            bindingResult
//                    .rejectValue("username", "error.user",
//                            "There is already a user registered with the username provided");
//        }
//        if (bindingResult.hasErrors()) {
//            modelAndView.setViewName("registration");
//        } else {
//            userService.saveUser(userDto);
//            modelAndView.addObject("successMessage", "User has been registered successfully");
//            modelAndView.addObject("user", new UserDto());
//            modelAndView.setViewName("registration");
//
//        }
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
//    ModelAndView home() {
//        ModelAndView modelAndView = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        UserDto user = userService.findUserByEmail(auth.getName());
//        modelAndView.addObject("userName", "Welcome " + user.getUsername());
//        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
//        modelAndView.setViewName("admin/home");
//        return modelAndView;
//    }

}