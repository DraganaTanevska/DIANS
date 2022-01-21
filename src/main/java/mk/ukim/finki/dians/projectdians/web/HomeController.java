package mk.ukim.finki.dians.projectdians.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {
    @GetMapping
    public String getHomePage(Model model) throws IOException, InterruptedException {
        model.addAttribute("bodyContent", "home");
        return "home";
        //tuka bese master-template
    }

    @GetMapping("/access_denied")
    public String getAccessDeniedPage(Model model) {
        model.addAttribute("bodyContent", "access_denied");
        return "home";
        //tuka bese master-template
    }

}
