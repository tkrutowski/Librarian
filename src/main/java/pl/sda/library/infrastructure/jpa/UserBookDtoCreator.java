package pl.sda.library.infrastructure.jpa;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.UserBook;

import java.time.LocalDate;
import java.util.Optional;

@Component
@AllArgsConstructor
public class UserBookDtoCreator {

    private BookstoreDtoRepository bookstoreDtoRepository;
    private BookDtoRepository bookDtoRepository;
    private UserDtoRepository userDtoRepository;


    UserBookDto fromDomain(UserBook userBook) {
        UserBookDto userBookDto = new UserBookDto();
        userBookDto.setId(userBook.getId());
        userBookDto.setBook(getBookById(userBook.getIdBook()));
        userBookDto.setUser(getUser(userBook.getIdUser()));
        userBookDto.setBookstore(getBookstoreFromString(userBook.getBookstore()));
        userBookDto.setEditionType(userBook.getEditionType());
        userBookDto.setReadingStatus(userBook.getReadingStatus());
        userBookDto.setOwnershipStatus(userBook.getOwnershipStatus());
        userBookDto.setReadFrom(userBook.getReadFrom());
        userBookDto.setReadTo(userBook.getReadTo());
        userBookDto.setInfo(userBook.getInfo());
        return userBookDto;
    }

    private BookstoreDto getBookstoreFromString(String bookstore) {
        Optional<BookstoreDto> bookstoreDtoByName = bookstoreDtoRepository.findBookstoreDtoByName(bookstore);
        if (bookstoreDtoByName.isPresent()) {
            return bookstoreDtoByName.get();
        }
        return new BookstoreDto();
    }

    private BookDto getBookById(Long id){
        Optional<BookDto> byId = bookDtoRepository.findById(id);
        if(byId.isPresent()) {
            return byId.get();
        }
        return new BookDto();
    }

    private UserDto getUser(Long id) {
        Optional<UserDto> userDto = userDtoRepository.findById(id);
        if (userDto.isPresent())
            return userDto.get();

        return new UserDto();
    }
}
