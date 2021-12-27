package mk.ukim.finki.dians.projectdians.web;

import mk.ukim.finki.dians.projectdians.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService authService;
    public LoginController(UserService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getLoginPage() {
       // model.addAttribute("bodyContent","login");
        return "login";
    }
    @PostMapping
    public String UserLogin(@RequestParam String username, @RequestParam String password)
    {
        if(authService.CheckIfExistLogin(username,password))
        return "home";
        else return "/home?error=NonExistingUser";
    }
    @PostMapping("/delete/{username}")
    public String DeleteUser(@PathVariable String username)
    {
        authService.DeleteUser(username);
        return "/home";
    }
}
