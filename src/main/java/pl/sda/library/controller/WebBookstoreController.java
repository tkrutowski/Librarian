package pl.sda.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.sda.library.domain.model.Bookstore;
import pl.sda.library.domain.service.BookstoreService;

@AllArgsConstructor
@Controller
@RequestMapping("/bookstores")
public class WebBookstoreController {

    BookstoreService bookstoreService;

    @GetMapping
    public String getAllBookstores(Model model) {
        model.addAttribute(new Bookstore());
        model.addAttribute("bookstoreList", bookstoreService.findAllBookstores());
        return "bookstores";
    }

    @PostMapping
    public String addBookstore(Bookstore bookstore) {
        bookstoreService.addBookstore(bookstore);
        return "redirect:/bookstores";
    }

    @RequestMapping(value="/delete/{bookstoreId}",method = RequestMethod.GET)
    public String delBookstore(@PathVariable String bookstoreId) {
        bookstoreService.deleteBookstore(Long.parseLong(bookstoreId));
        return "redirect:/bookstores";
    }
}
