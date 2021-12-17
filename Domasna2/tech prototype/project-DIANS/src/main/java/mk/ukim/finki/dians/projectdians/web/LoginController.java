package mk.ukim.finki.dians.projectdians.web;

import mk.ukim.finki.dians.projectdians.model.User;
import mk.ukim.finki.dians.projectdians.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
