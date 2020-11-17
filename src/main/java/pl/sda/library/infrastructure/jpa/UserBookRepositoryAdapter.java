package pl.sda.library.infrastructure.jpa;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.UserBook;
import pl.sda.library.domain.port.UserBookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class UserBookRepositoryAdapter implements UserBookRepository {

    private UserBookDtoRepository userBookDtoRepository;
    private UserBookDtoCreator userBookDtoCreator;

    @Override
    public Optional<UserBook> add(UserBook userBook) {
        UserBookDto userBookDto = userBookDtoCreator.fromDomain(userBook);
        return Optional.of(userBookDtoRepository.save(userBookDto).toDomain());
    }

    @Override
    public Optional<UserBook> edit(UserBook userBook) {
        UserBookDto userBookDto = userBookDtoCreator.fromDomain(userBook);
        return Optional.of(userBookDtoRepository.save(userBookDto).toDomain());
    }

    @Override
    public void delete(Long id) {
        userBookDtoRepository.findById(id)
                .ifPresent(userBookDto -> userBookDtoRepository.delete(userBookDto));
    }

    @Override
    public List<UserBook> findAll() {
        List<UserBook> userBooksList = new ArrayList<>();
        userBookDtoRepository.findAll()
                .iterator()
                .forEachRemaining(userBookDto -> userBooksList.add(userBookDto.toDomain()));
        return userBooksList;
    }

    @Override
    public Optional<UserBook> findById(Long id) {
        return userBookDtoRepository.findById(id)
                .map(userBookDto -> userBookDto.toDomain());
    }
}
