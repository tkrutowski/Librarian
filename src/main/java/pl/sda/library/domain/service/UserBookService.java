package pl.sda.library.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.library.domain.model.UserBook;
import pl.sda.library.domain.model.exception.UserBookAlreadyExistException;
import pl.sda.library.domain.model.exception.UserBookDoesNotExistException;
import pl.sda.library.domain.port.UserBookRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserBookService {

    private UserBookRepository userBookRepository;

    public UserBook addUserBook(UserBook userBook) {
        int i=0;
        if (isUserBookExist(userBook))
            throw new UserBookAlreadyExistException(userBook);

        return userBookRepository.add(userBook).get();
    }

    public UserBook editUserBook(UserBook userBook, Long id) {
        Optional<UserBook> userBookById = userBookRepository.findById(id);
        if (!userBookById.isPresent()) {
            throw new UserBookDoesNotExistException(id);
        }

        userBookById.get().setBookstore(userBook.getBookstore());
        //TODO po zmianie trzeba sprawdzić czy już taki nie istnieje
        userBookById.get().setEditionType(userBook.getEditionType());
        userBookById.get().setReadingStatus(userBook.getReadingStatus());
        userBookById.get().setOwnershipStatus(userBook.getOwnershipStatus());
        userBookById.get().setReadFrom(userBook.getReadFrom());
        userBookById.get().setReadTo(userBook.getReadTo());
        userBookById.get().setInfo(userBook.getInfo());
        userBookById.get().setIsRead(userBook.getIsRead());

        return userBookRepository.edit(userBookById.get()).get();
    }

    public void deleteUserBook(Long id) {
        userBookRepository.delete(id);
    }

    public UserBook findUserBook(Long id) {
        Optional<UserBook> userBookById = userBookRepository.findById(id);
        if (!userBookById.isPresent()) {
            throw new UserBookDoesNotExistException(id);
        }
        return userBookById.get();
    }

    public List<UserBook> findAllUserBooks() {
        return userBookRepository.findAll();
    }

    private boolean isUserBookExist(UserBook userBookToCheck) {
        List<UserBook> allByBook = userBookRepository.findAllByIdBook(userBookToCheck.getIdBook());
        long count = allByBook.stream()
                .filter(userBook -> userBook.getIdUser() == userBookToCheck.getIdUser())
                .filter(userBook -> userBook.getEditionType().equals(userBookToCheck.getEditionType()))
                .count();

        return count > 0 ? true : false;
    }
}
