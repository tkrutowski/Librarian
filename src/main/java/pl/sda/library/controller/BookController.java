package pl.sda.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.library.infrastructure.upolujebooka.UpolujebookaAdapter;

import pl.sda.library.model.Book;
import pl.sda.library.model.ProductRepository;
import pl.sda.library.service.BookService;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/upolujebooka/{name}")
    public List<Book> getBook(@PathVariable String name){
        List<Book> getBooksFromUpolujEbooka = new ArrayList<>();
        UpolujebookaAdapter upolujebookaAdapter = new UpolujebookaAdapter();
        getBooksFromUpolujEbooka = upolujebookaAdapter.findByPhrase(name);
        return getBooksFromUpolujEbooka;
    }
}
