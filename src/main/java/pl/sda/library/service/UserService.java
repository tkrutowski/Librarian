package pl.sda.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.library.model.User;
import pl.sda.library.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    boolean addUser(User user){
        //TODO - sprawdzić przed dodaniem czy w bazie nie istnieje już
        userRepository.addUser(user);
        return true;
    }

    List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }

    public void delUser(long id) {
        userRepository.deleteUser(id);
    }
}
