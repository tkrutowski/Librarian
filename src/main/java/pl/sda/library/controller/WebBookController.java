package pl.sda.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.service.BookService;

@AllArgsConstructor
@Controller
@RequestMapping("/books")
public class WebBookController {
    BookService bookService;

    @GetMapping
    public String getAllBooks(Model model) {
        model.addAttribute(new Book());
        model.addAttribute("bookList", bookService.getAllBooks());
        return "books";
    }

    @PostMapping
    public String addBook(Book book) {
        bookService.addBook(book);
        return "redirect:/books";
    }

    @RequestMapping(value="/delete/{bookId}",method = RequestMethod.GET)
    public String delBook(@PathVariable String bookId) {
        bookService.deleteBook(Long.parseLong(bookId));
        return "redirect:/books";
    }
}
