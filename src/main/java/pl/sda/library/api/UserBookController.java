package pl.sda.library.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.library.domain.model.UserBook;
import pl.sda.library.domain.service.UserBookService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/userbooks")
public class UserBookController {

    private UserBookService userBookService;

    @PostMapping
    public UserBook addUserBook(@RequestBody UserBook userBook){
        return userBookService.addUserBook(userBook);
    }

    @PutMapping("/{id}")
    public UserBook editUserBook(@RequestBody UserBook userBook, @PathVariable Long id) {
        return userBookService.editUserBook(userBook, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserBook(@PathVariable Long id) {
        userBookService.deleteUserBook(id);
    }

    @GetMapping("/{id}")
    public UserBook getUserBook(@PathVariable Long id){
        return userBookService.findUserBook(id);
    }

    @GetMapping
    public List<UserBook> getAllUserBooks() {
        return userBookService.findAllUserBooks();
    }
}
