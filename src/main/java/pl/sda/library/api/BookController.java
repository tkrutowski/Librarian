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
import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.service.BookService;
import pl.sda.library.infrastructure.upolujebooka.UpolujebookaAdapter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BookController {

    BookService bookService;

    @PostMapping
    public Boolean addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @PutMapping("/id")
    public Book editBookstore(@RequestBody Book book, @PathVariable Long id) {
        return bookService.editBook(book, id);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id){
        return bookService.findBook(id);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    //TODO - to powinno iść do servisu
    @GetMapping("/upolujebooka/{name}")
    public List<Book> getBook(@PathVariable String name){
        List<Book> getBooksFromUpolujEbooka = new ArrayList<>();
        UpolujebookaAdapter upolujebookaAdapter = new UpolujebookaAdapter();
        getBooksFromUpolujEbooka = upolujebookaAdapter.findByPhrase(name);
        return getBooksFromUpolujEbooka;
    }
}
