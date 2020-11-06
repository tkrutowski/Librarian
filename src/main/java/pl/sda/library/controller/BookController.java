package pl.sda.library.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.sda.library.model.Book;
import pl.sda.library.service.BookService;

@AllArgsConstructor
//@NoArgsConstructor
@RestController
public class BookController {

    BookService bookService;

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable Long id){
        int i=0;
        return bookService.getBook(id);
    }

    @PostMapping("/books/add")
    public Boolean addBook(@RequestBody Book book){
        return bookService.addBook(book);

    }
}
