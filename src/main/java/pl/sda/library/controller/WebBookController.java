package pl.sda.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.service.BookService;
import pl.sda.library.infrastructure.upolujebooka.UpolujebookaScrapper;

import java.net.MalformedURLException;

@AllArgsConstructor
@Controller
@RequestMapping("/books")
public class WebBookController {
    BookService bookService;

    @GetMapping
    public String getAllBooks(Model model) {
        model.addAttribute(new Book());
        model.addAttribute("bookList", bookService.findAllBooks());
        return "book";
    }

    @PostMapping
    public String addBook(String url, Book book, Model model) throws MalformedURLException {
        model.addAttribute(new Book());
        if ((book.getAuthors() != null )||(book.getTitle() != null) ){
            bookService.addBook(book);
         //   return "redirect:/userbooks";//
            return "book";//
        }
        if (url != null){
            model.addAttribute("bookFromUrl", UpolujebookaScrapper.findBookFromUrl(url));
        }
        model.addAttribute("bookList", bookService.findAllBooks());
        return "book";
    }

    @RequestMapping(value="/delete/{bookId}",method = RequestMethod.GET)
    public String delBook(@PathVariable String bookId) {
        bookService.deleteBook(Long.parseLong(bookId));
        return "book";
    }
}
