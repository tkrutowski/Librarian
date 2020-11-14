package pl.sda.library.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.library.domain.model.User;
import pl.sda.library.domain.port.UserRepository;
import pl.sda.library.domain.model.exception.ObjectAlreadyExistException;
import pl.sda.library.domain.model.exception.ObjectDoesNotExistException;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public Long addUser(User user){
        if(alreadyExist(user))
            throw new ObjectAlreadyExistException("Podany autor już istnieje w bazie danych.");
        Long id = userRepository.addUser(user);
        return id;
    }

    private boolean alreadyExist(User user) {
        return userRepository.isExist(user.getLogin());
    }

    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }

    public void delUser(long id) {
        userRepository.deleteUser(id);
    }

    public User getUser(Long id) {
        User userById = userRepository.getUserById(id);
        if(userById.getIdUser() == null)
            throw new ObjectDoesNotExistException("Podany użytkownik nie istnieje w bazie danych.");
        else
            return userById;
    }

    public User editUser(User user) {
        User userById = userRepository.getUserById(user.getIdUser());
        if(userById.getIdUser() == null)
            throw new ObjectDoesNotExistException("Podany użytkownik nie istnieje w bazie danych.");

        userById.setName(user.getName());
        //TODO zastanowić się nad edycją hasło w osobnej metodzie
        userById.setPassword(user.getPassword());
        //TODO zastanowić się nad edycją isAdmin w osobnej metodzie
        userById.setIsAdmin(user.getIsAdmin());

        return userRepository.editUser(userById);
    }
}
