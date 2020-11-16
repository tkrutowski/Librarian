package pl.sda.library.infrastructure.jpa;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.User;
import pl.sda.library.domain.port.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private UserDtoRepository userDtoRepository;

    @Override
    public Long add(User user) {
        return userDtoRepository.save(UserDto.fromDomain(user)).getId();
    }

    @Override
    public Optional<User> edit(User user) {
        return Optional.of(userDtoRepository.save(UserDto.fromDomain(user)).toDomain());
    }

    @Override
    public void delete(Long id) {
        userDtoRepository.findById(id)
                .ifPresent(user -> userDtoRepository.delete(user));
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        userDtoRepository.findAll()
                .iterator()
                .forEachRemaining(userDto -> userList.add(userDto.toDomain()));
        return userList;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userDtoRepository.findById(id)
                .map(userDto -> userDto.toDomain());
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userDtoRepository.findUserDtoByLogin(login)
                .map(userDto -> userDto.toDomain());
    }
}
