package pl.sda.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sda.library.domain.model.Author;
import pl.sda.library.domain.model.Bookstore;
import pl.sda.library.domain.model.User;
import pl.sda.library.domain.service.AuthorService;
import pl.sda.library.domain.service.AuthorValidator;
import pl.sda.library.domain.service.UserService;

@AllArgsConstructor
@Controller
@RequestMapping("/authors")
public class WebAuthorController {
    private AuthorService authorService;
    private AuthorValidator authorValidator;
    private UserService userService;

    @GetMapping
    public String getAllAuthors(Model model) {
        model.addAttribute(new Author());
        model.addAttribute("authorList", authorService.findAllAuthors());
        model.addAttribute("logged", getUser().getId() > 0? true : false);
        return "authors";
    }


    @PostMapping
    public String addAuthor(@ModelAttribute("author") Author authorForm, BindingResult bindingResult) {
        authorValidator.validate(authorForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/authors";
        }
        authorService.addAuthor(authorForm);

        return "redirect:/authors";
    }

    @RequestMapping(value="/delete/{authorId}",method = RequestMethod.GET)
    public String delAuthor(@PathVariable String authorId) {
        authorService.deleteAuthor(Long.parseLong(authorId));
        return "redirect:/authors";
    }

    private User getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null || !authentication.getPrincipal().equals("anonymousUser")) {
            return userService.findUserByUserName(authentication.getName());
        }
        return new User();
    }
}
