package pl.sda.library.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.model.User;
import pl.sda.library.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class BookService {

    private BookRepository bookRepository ;

    public Book getBook(Long id){
        return bookRepository.findByIdBook(id);
    }

    public boolean addBook(Book book){

        return bookRepository.addBook(book);
    }
    public List<Book> getAllBooksByUser(User user) {


        return bookRepository.findAllBooksByUser(user);
    }
    public List<Book> getAllBooks(){
        return new ArrayList<>();
        }
}

