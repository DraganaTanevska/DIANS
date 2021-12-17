package mk.ukim.finki.dians.projectdians.web;

import mk.ukim.finki.dians.projectdians.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @PostMapping
    public String toLoginPage(@RequestParam String name,@RequestParam String email,@RequestParam String password,@RequestParam String username,@RequestParam String surname)
    {
        userService.Register(username,name,surname,email,password);
        return "login";
    }
}
