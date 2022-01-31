package mk.ukim.finki.dians.user.service;

import mk.ukim.finki.dians.user.model.Role;
import mk.ukim.finki.dians.user.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    User Register(String username, String name, String surname, String email, String password, Role role);

    User CheckIfExistLogin(String username, String password);

    User Login(String username, String password);

}