package pl.sda.library.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.library.domain.model.User;
import pl.sda.library.domain.model.exception.UserAlreadyExistException;
import pl.sda.library.domain.model.exception.UserDoesNotExistException;
import pl.sda.library.domain.port.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public Long addUser(User user){
        Optional<User> optionalUser = userRepository.findByLogin(user.getUsername());
        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistException(user);
        }
        return userRepository.add(user);
    }

    //TODO zastanowić się nad edycją hasło w osobnej metodzie
    public User editUser(User userToEdit, Long id) {
        Optional<User> userById = userRepository.findById(id);
        if (!userById.isPresent()) {
            throw new UserDoesNotExistException(id);
        }

        userById.get().setName(userToEdit.getName());
        userById.get().setPassword(userToEdit.getPassword());

        return userRepository.edit(userById.get()).get();
    }

    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    public User findUser(Long id) {
        Optional<User> userById = userRepository.findById(id);
        if (!userById.isPresent()) {
            throw new UserDoesNotExistException(id);
        }
            return userById.get();
    }

    public User findUserByUserName(String username) {
        Optional<User> userByUserName = userRepository.findByLogin(username);
        if (!userByUserName.isPresent()) {
            throw new UserDoesNotExistException(username);
        }
        return userByUserName.get();
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }
}
