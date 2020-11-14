package pl.sda.library.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
//@NoArgsConstructor
@Component
public class BookRepository {
//public abstract class BookRepository implements BookDtoRepository{
    private BookDtoRepository bookDtoRepository;
    private DtoFactory dtoFactory;

    public Book findByIdBook(Long id){
        Book book;
        Optional<BookDto> byId = bookDtoRepository.findById(id);
        if(byId.isPresent()) {
            book = byId.get().toModel();
        } else
            book=new Book();
        return book;
    }

    public boolean addBook(Book book) {
        int i=0;
        BookDto bookDto = dtoFactory.createBookDto(book);
        bookDtoRepository.save(bookDto);
        return true;
    }


    public List<Book> findAllBooksByUser(User user) {
        UserDto userDto = dtoFactory.createUserDto(user);
        List<BookDto> listDto=new ArrayList<>();
        bookDtoRepository.findAllByUser(userDto)
                .iterator()
                .forEachRemaining(listDto::add);
        return listDto.stream().map(BookDto::toModel).collect(Collectors.toList());
    }
}
