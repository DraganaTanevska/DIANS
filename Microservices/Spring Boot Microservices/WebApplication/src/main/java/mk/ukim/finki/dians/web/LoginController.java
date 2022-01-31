package mk.ukim.finki.dians.web;

import mk.ukim.finki.dians.service.UserService;
import org.bouncycastle.math.raw.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    private final UserService authService;

    public LoginController(UserService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        // model.addAttribute("bodyContent","login");
        return "loginPage";
    }

    /**
     * Both username and password must belong to the same user
     * @param username - Checks if user with username exists
     * @param password - Checks if user with password exists
     * @return homePage.html
     */
    //ako ne raboti vrati go so check toa go smeni.
    @PostMapping("/login")

    public String UserLogin(@RequestParam String username, @RequestParam String password) {
        if (authService.Login(username, password)!=null)
            return "homePage";
        else return "/homePage?error=NonExistingUser";
    }


}
