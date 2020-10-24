package pl.sda.library.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.library.model.Book;
import pl.sda.library.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Component
public class BookService {
    private BookRepository bookRepository;

    public Book getBook(Long id){
        return new Book();
    }

    public List<Book> getAllBooks(){
        return new ArrayList<>();
    }
}
