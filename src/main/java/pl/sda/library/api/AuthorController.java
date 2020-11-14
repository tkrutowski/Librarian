package pl.sda.library.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.sda.library.domain.model.Author;
import pl.sda.library.domain.service.AuthorService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/authors")
public class AuthorController {
    AuthorService authorService;

    @GetMapping("/all")
    public List<Author> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @PostMapping("/add")
    public Long addAuthor(@RequestBody Author author)  {
        return authorService.addAuthor(author);
    }

    @PutMapping("/edit")
    public Author editAuthor(@RequestBody Author author)  {
        return authorService.editAuthor(author);
    }

    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable Long id){
        return authorService.getAuthor(id);
    }

    @DeleteMapping("/{id}")
    public void delAuthor(@PathVariable Long id){
        authorService.delAuthor(id);
    }
}
