package mk.ukim.finki.dians.projectdians.web;

import mk.ukim.finki.dians.projectdians.model.Role;
import mk.ukim.finki.dians.projectdians.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {
    // private final AuthService authService;
    private final UserService userService;

    public RegisterController(UserService userService) {
        // this.authService = authService;
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage() {
        return "register";
    }

    /**
     *
     * @param name - Makes new user with given name
     * @param email - Makes new user with given email
     * @param password - Makes new user with given password
     * @param username - Makes new user with given username
     * @param surname - Makes new user with given surname
     * @param role - Makes new user with given role
     * @return login.html
     */
    @PostMapping
    public String toLoginPage(@RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam String username, @RequestParam String surname, @RequestParam Role role) {
        userService.Register(username, name, surname, email, password, role);
        return "login";
    }
}
