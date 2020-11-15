package pl.sda.library.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.library.domain.model.Bookstore;
import pl.sda.library.domain.service.BookstoreService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/bookstores")
public class BookstoreController {

    BookstoreService bookstoreService;

    @GetMapping
    public List<Bookstore> getAllBookstores() {
        return bookstoreService.findAllBookstores();
    }

    @PostMapping
    public Long addBookstore(@RequestBody Bookstore bookstore) {
        return bookstoreService.addBookstore(bookstore);
    }

    @PutMapping
    public Bookstore editBookstore(@RequestBody Bookstore bookstore) {
        return bookstoreService.editBookstore(bookstore);
    }

    @GetMapping("/{id}")
    public Bookstore getBookstore(@PathVariable Long id) {
        return bookstoreService.findBookstore(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBookstore(@PathVariable Long id) {
        bookstoreService.deleteBookstore(id);
    }
}
