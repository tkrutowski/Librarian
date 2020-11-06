package pl.sda.library.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.library.model.User;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class UserRepository {

    private UserDtoRepository userDtoRepository;
    private DtoFactory dtoFactory;

    public boolean addUser(User user) {
        userDtoRepository.save(dtoFactory.createUserDto(user));
        return true;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        userDtoRepository.findAll().iterator().forEachRemaining(userDto -> userList.add(userDto.toModel()));
        return userList;
    }

    public void deleteUser(long id) {
        userDtoRepository.deleteById(id);
    }
}
