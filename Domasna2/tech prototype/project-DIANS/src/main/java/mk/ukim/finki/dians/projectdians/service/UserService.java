package mk.ukim.finki.dians.projectdians.service;

import mk.ukim.finki.dians.projectdians.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User Register(String username, String name, String surname, String email, String password);
    boolean CheckIfExistLogin(String username,String password);
}
