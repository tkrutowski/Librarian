package pl.sda.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.library.domain.model.EditionType;
import pl.sda.library.domain.model.OwnershipStatus;
import pl.sda.library.domain.model.ReadingStatus;
import pl.sda.library.domain.model.UserBook;
import pl.sda.library.domain.service.BookService;
import pl.sda.library.domain.service.BookstoreService;
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

    @GetMapping
    public String findAllUsers(Model model){

        List<EditionType> editionTypes = Arrays.asList(EditionType.values());
        List<ReadingStatus> readingStatus = Arrays.asList(ReadingStatus.values());
        List<OwnershipStatus> ownershipStatus = Arrays.asList(OwnershipStatus.values());
        model.addAttribute(new UserBook());
        model.addAttribute("userList", userService.findAllUsers());
        model.addAttribute("bookList", bookService.findAllBooks());
        model.addAttribute("bookstoreList", bookstoreService.findAllBookstores());
        model.addAttribute("editionTypes", editionTypes);
        model.addAttribute("readingStatus", readingStatus);
        model.addAttribute("ownershipStatus", ownershipStatus);
        model.addAttribute("userBookListAll", userBookService.findAllUserBooks());
        return "userbooks";

    }

    @PostMapping
    public String addUserBook( UserBook userBook)  {
        int i=0;
        userBookService.addUserBook(userBook);
        return "redirect:/userbooks";
    }
}

