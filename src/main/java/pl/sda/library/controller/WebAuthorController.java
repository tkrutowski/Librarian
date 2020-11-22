package pl.sda.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.library.domain.model.Author;
import pl.sda.library.domain.service.AuthorService;

@AllArgsConstructor
@Controller
@RequestMapping("/authors")
public class WebAuthorController {
    AuthorService authorService;

    @GetMapping
    public String getAllAuthors(Model model) {
        model.addAttribute(new Author());
        model.addAttribute("authorList", authorService.findAllAuthors());
        return "authors";
    }

    @PostMapping
    public String addAuthor(Author author) {
        authorService.addAuthor(author);
        return "redirect:/authors";
    }

    @RequestMapping(value="/delete/{authorId}",method = RequestMethod.GET)
    public String delAuthor(@PathVariable String authorId) {
        authorService.deleteAuthor(Long.parseLong(authorId));
        return "redirect:/authors";
    }
}
