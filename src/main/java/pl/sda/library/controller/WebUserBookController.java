package pl.sda.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.model.BookViewOption;
import pl.sda.library.domain.model.EditionType;
import pl.sda.library.domain.model.OwnershipStatus;
import pl.sda.library.domain.model.ReadingStatus;
import pl.sda.library.domain.model.Series;
import pl.sda.library.domain.model.UserBook;
import pl.sda.library.domain.service.BookService;
import pl.sda.library.domain.service.BookstoreService;
import pl.sda.library.domain.service.SeriesService;
import pl.sda.library.domain.service.UserBookService;
import pl.sda.library.domain.service.UserService;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/userbooks")
public class WebUserBookController {
    UserBookService userBookService;
    UserService userService;
    BookService bookService;
    BookstoreService bookstoreService;
    SeriesService seriesService;
    List<UserBook> userBookList;
    List<Book> bookList;

    private void createModel(Model model) {
        model.addAttribute("userList", userService.findAllUsers());
        model.addAttribute("bookList", bookList);
        model.addAttribute("bookstoreList", bookstoreService.findAllBookstores());
        model.addAttribute("userBookList", userBookList);
        model.addAttribute("seriesList", seriesService.findAllSeries());
        model.addAttribute("ownershipStatus", Arrays.asList(OwnershipStatus.values()));
        model.addAttribute("editionType", Arrays.asList(EditionType.values()));
        model.addAttribute("readingStatus", Arrays.asList(ReadingStatus.values()));
        model.addAttribute(new UserBook());
    }

    @GetMapping("/reloaded/{view}")
    public String findUsersBooksReloaded(Model model, @PathVariable String view) {
        BookViewOption.getInstance().setBookViewString(view);
        bookList =  bookService.findAllBooks();

        createModel(model);
        return getHtmlBookView();
    }

    @GetMapping("/status/{status}")
    public String findUsersBooksToRead(Model model, @PathVariable String status) {
        String infoBarText = getInfoReadingStatus(ReadingStatus.valueOf(status));
        userBookList = userBookService.findAllUserBooksByReadingStatus(ReadingStatus.valueOf(status));
        bookList =  bookService.findAllBooks();
        createModel(model);
        model.addAttribute("infoBar", infoBarText);
        return getHtmlBookView();
    }

    @GetMapping("/series/{title}")
    public String findUsersBooksBySeries(Model model, @PathVariable String title) {
        String infoBarText = title;
        Series seriesByTitle = seriesService.findSeriesByTitle(title);
        bookList = bookService.findAllBooksBySeries(seriesByTitle);
        userBookList = userBookService.findAllUserBooksBySeries(bookList);
        createModel(model);
        model.addAttribute("infoBar", infoBarText);
        return getHtmlBookView();
    }

    @GetMapping
    public String findUsersBooksToRead(Model model) {
        bookList =  bookService.findAllBooks();
        createModel(model);
        return "userbooks";
    }

    @PostMapping
    public String addUserBook(UserBook userBook) {
        userBookService.addUserBook(userBook);
        return "redirect:/userbooks";
    }

    private String getHtmlBookView() {
        switch (BookViewOption.getInstance().getViewType()) {
            case LARGE:
                return "userbooks-largeview";
            case MEDIUM:
                return "userbooks-mediumview";
            case SMALL:
                return "userbooks-smallview";
            default:
                return "userbooks-smallview";
        }
    }

    private String getInfoReadingStatus(ReadingStatus status) {
        switch (status) {
            case NOT_READ:
                return "Książki które chce przeczytać";
            case READ_NOW:
                return "Książki które teraz czytam";
            case READ:
                return "Książki które przeczytałem";
            default:
                return "";
        }
    }

}

