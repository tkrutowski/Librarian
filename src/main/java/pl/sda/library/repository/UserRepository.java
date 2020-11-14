package pl.sda.library.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.User;
import pl.sda.library.domain.model.exception.ObjectDoesNotExistException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class UserRepository {

    private UserDtoRepository userDtoRepository;
    private DtoFactory dtoFactory;

    public Long addUser(User user) {
        Long id = 0L;
        UserDto saved = userDtoRepository.save(dtoFactory.createUserDto(user));
        id = saved.getIdUser();
        return id;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        userDtoRepository.findAll().iterator().forEachRemaining(userDto -> userList.add(userDto.toModel()));
        return userList;
    }

    public void deleteUser(long id) {
        if (isExistById(id))
            userDtoRepository.deleteById(id);
        else
            throw new ObjectDoesNotExistException("Nie ma użytkownika o takim ID: " + id);

    }

    public User getUserById(Long idUser) {
        Optional<UserDto> byId = userDtoRepository.findById(idUser);
        if (byId.isPresent())
            return byId.get().toModel();
        else
            return new User();
    }

    public boolean isExistById(Long id) {
        Optional<UserDto> byId = userDtoRepository.findById(id);
        if (byId.isPresent())
            return true;
        else
            return false;
    }

    public boolean isExist(String login) {
        Optional<UserDto> userDto = userDtoRepository.findUserDtoByLogin(login);
        if (userDto.isPresent())
            return true;
        else
            return false;
    }

    public User editUser(User user) {
        UserDto saved = userDtoRepository.save(dtoFactory.createUserDto(user));
        return saved.toModel();
    }
}
