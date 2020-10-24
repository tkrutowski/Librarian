package pl.sda.library.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.library.model.Book;
import pl.sda.library.service.BookService;

@AllArgsConstructor
@NoArgsConstructor
@RestController
public class BookController {

    BookService bookService;

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable Long id){
        return new Book();// bookService.getBook(id);
    }
}
