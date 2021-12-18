package mk.ukim.finki.dians.projectdians.service.impl;

import mk.ukim.finki.dians.projectdians.model.User;
import mk.ukim.finki.dians.projectdians.repository.UserRepository;
import mk.ukim.finki.dians.projectdians.service.UserService;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User Register(String username, String name, String surname, String email, String password) {
        User newUser = new User(username,name,surname,email,password);
        return this.userRepository.save(newUser);
    }

    @Override
    public boolean CheckIfExistLogin(String username, String password) {
        User existingUser = this.userRepository.findByUsernameAndPassword(username,password);
        if(existingUser != null)
            return true;
        else return false;
    }
    public User Login(String username,String password)
    {
        return userRepository.findByUsernameAndPassword(username,password);
    }
    public void DeleteUser(String username)
    {
        userRepository.delete(userRepository.getById(username));
    }
}
