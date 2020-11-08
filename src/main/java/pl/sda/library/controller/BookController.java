package pl.sda.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.sda.library.model.Book;
import pl.sda.library.service.BookService;

@AllArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

    BookService bookService;

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id){
        return bookService.getBook(id);
    }

    @PostMapping("/add")
    public Boolean addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }
}
