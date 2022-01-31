package mk.ukim.finki.dians.user.web;

import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.dians.user.model.Role;
import mk.ukim.finki.dians.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/user/register")
public class RegisterController {
    // private final AuthService authService;
    private final UserService userService;

    public RegisterController(UserService userService) {
        // this.authService = authService;
        this.userService = userService;
    }


    /**
     *
     * @param send: string of all params
     * @return login.html
     */
    @PostMapping("/register/{send}")
    public String toLoginPage(@PathVariable String send){
        String [] strings=send.split("\\+");
        String username=strings[0];
        String name=strings[1];
        String surname=strings[2];
        String email=strings[3];
        String password=strings[4];
        Role role=Role.valueOf(strings[5]);
        userService.Register(username, name, surname, email, password, role);
        return "login";
    }
}
