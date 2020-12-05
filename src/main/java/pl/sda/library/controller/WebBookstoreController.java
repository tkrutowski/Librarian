package pl.sda.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.sda.library.domain.model.Bookstore;
import pl.sda.library.domain.model.Role;
import pl.sda.library.domain.model.User;
import pl.sda.library.domain.service.BookstoreService;
import pl.sda.library.domain.service.BookstoreValidator;
import pl.sda.library.domain.service.UserService;

@AllArgsConstructor
@Controller
@RequestMapping("/bookstores")
public class WebBookstoreController {

    private BookstoreService bookstoreService;
    private BookstoreValidator bookstoreValidator;
    private UserService userService;

    @GetMapping
    public String getAllBookstores(Model model) {
        model.addAttribute(new Bookstore());
        model.addAttribute("logged", getUser().getId() > 0? true : false);
        model.addAttribute("bookstoreList", bookstoreService.findAllBookstores());
        return "bookstores";
    }

    @PostMapping
    public String addBookstore(@ModelAttribute("bookstore") Bookstore bookstoreForm, BindingResult bindingResult) {
        bookstoreValidator.validate(bookstoreForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/bookstores";
        }
        bookstoreService.addBookstore(bookstoreForm);

        return "redirect:/bookstores";
    }

    @RequestMapping(value="/delete/{bookstoreId}",method = RequestMethod.GET)
    public String delBookstore(@PathVariable String bookstoreId) {
        bookstoreService.deleteBookstore(Long.parseLong(bookstoreId));
        return "redirect:/bookstores";
    }

    private User getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null || !authentication.getPrincipal().equals("anonymousUser")) {
            return userService.findUserByUserName(authentication.getName());
        }
        return new User();
    }
}
