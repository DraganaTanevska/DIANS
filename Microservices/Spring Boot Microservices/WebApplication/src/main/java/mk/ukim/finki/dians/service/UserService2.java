package mk.ukim.finki.dians.service;

import mk.ukim.finki.dians.model.Role;
import mk.ukim.finki.dians.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService2 extends UserDetailsService {
    User Register(String username, String name, String surname, String email, String password, Role role);

   // boolean CheckIfExistLogin(String username, String password);

    User Login(String username, String password);

}
