package pl.sda.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.service.BookService;
import pl.sda.library.domain.service.BookValidator;
import pl.sda.library.infrastructure.upolujebooka.UpolujebookaScrapper;

@AllArgsConstructor
@Controller
@RequestMapping("/books")
public class WebBookController {
    private BookService bookService;
    private BookValidator bookValidator;

    @GetMapping("/findBook")
    public String findBook(Model model) {
        model.addAttribute(new Book());
        return "find-book";
    }

    @PostMapping("/findBook")
    public ModelAndView findBook(String url) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url", url);
        Book bookFromUrl = UpolujebookaScrapper.findBookFromUrl(url);

        if (bookFromUrl.getTitle() == null || bookFromUrl.getTitle().isEmpty()) {
            modelAndView.addObject("bookError", true);
            modelAndView.setViewName("find-book");
        }

        modelAndView.addObject("book", bookFromUrl);
        modelAndView.addObject("manual",false);
        modelAndView.setViewName("book");
        return modelAndView;
    }

    @GetMapping("/addManual")
    public ModelAndView addBooksManual() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject(new Book());
        modelAndView.addObject("manual",true);
        modelAndView.setViewName("book");
        return modelAndView;
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book bookForm, Model model, BindingResult bindingResult) {
        bookValidator.validate(bookForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "book";
        }
        bookService.addBook(bookForm);
        return "find-book";
    }

    @RequestMapping(value = "/delete/{bookId}", method = RequestMethod.GET)
    public String delBook(@PathVariable String bookId) {
        bookService.deleteBook(Long.parseLong(bookId));
        return "book";
    }
}
