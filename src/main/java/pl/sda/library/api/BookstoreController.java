package pl.sda.library.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.sda.library.domain.model.Bookstore;
import pl.sda.library.domain.service.BookstoreService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/bookstores")
public class BookstoreController {

    BookstoreService bookstoreService;

    @GetMapping("/all")
    public List<Bookstore> getAllAuthors(){
        return bookstoreService.getAllBookstores();
    }

    @PostMapping("/add")
    public Long addAuthor(@RequestBody Bookstore bookstore)  {
        return bookstoreService.addBookstore(bookstore);
    }

    @PutMapping("/edit")
    public Bookstore editAuthor(@RequestBody Bookstore bookstore)  {
        return bookstoreService.editBookstore(bookstore);
    }

    @GetMapping("/{id}")
    public Bookstore getAuthor(@PathVariable Long id){
        return bookstoreService.getBookstore(id);
    }

    @DeleteMapping("/{id}")
    public void delAuthor(@PathVariable Long id){
        bookstoreService.delBookstore(id);
    }
}
