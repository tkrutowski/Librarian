package pl.sda.library.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.sda.library.domain.model.Author;
import pl.sda.library.domain.service.AuthorService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    AuthorService authorService;

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @PostMapping
    public Long addAuthor(@RequestBody Author author) {
        return authorService.addAuthor(author);
    }

    @PutMapping
    public Author editAuthor(@RequestBody Author author) {
        return authorService.editAuthor(author);
    }

    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable Long id) {
        return authorService.getAuthor(id);
    }

    @DeleteMapping("/{id}")
    public void delAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
}
