package mk.ukim.finki.dians.projectdians.service;

import mk.ukim.finki.dians.projectdians.model.Role;
import mk.ukim.finki.dians.projectdians.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


public interface UserService extends UserDetailsService {
    User Register(String username, String name, String surname, String email, String password, Role role);
    boolean CheckIfExistLogin(String username,String password);
    User Login(String username,String password);
    void DeleteUser(String username);
}
