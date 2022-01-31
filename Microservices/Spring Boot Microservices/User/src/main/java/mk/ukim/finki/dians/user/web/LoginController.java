package mk.ukim.finki.dians.user.web;

import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.dians.user.model.Role;
import mk.ukim.finki.dians.user.model.User;
import mk.ukim.finki.dians.user.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/user/login")
public class LoginController {

    private final UserService authService;

    public LoginController(UserService authService) {
        this.authService = authService;
    }

    /**
     * Both username and password must belong to the same user
     * @param send - contains username and password
     * @return home.html
     */
    @PostMapping("/login/{send}")
    public User UserLogin(@PathVariable String send) {
        String [] strings=send.split("\\+");
        String username=strings[0];
        String password=strings[1];
        return authService.CheckIfExistLogin(username, password);
    }

    @GetMapping("/load/{s}")
    public UserDetails loadUser(@PathVariable String s)
    {
        return authService.loadUserByUsername(s);
    }

}
