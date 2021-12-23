package mk.ukim.finki.dians.projectdians.service.impl;

import mk.ukim.finki.dians.projectdians.model.Role;
import mk.ukim.finki.dians.projectdians.model.User;
import mk.ukim.finki.dians.projectdians.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.dians.projectdians.repository.UserRepository;
import mk.ukim.finki.dians.projectdians.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;

    }

    @Override
    public User Register(String username, String name, String surname, String email, String password, Role role) {
        User exist = userRepository.findByUsername(username).orElse(null);

        if(exist!=null)
        {
throw new UsernameAlreadyExistsException(username);
        }
        User newUser = new User(username,name,surname,email,passwordEncoder.encode(password),role);
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

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }

}
