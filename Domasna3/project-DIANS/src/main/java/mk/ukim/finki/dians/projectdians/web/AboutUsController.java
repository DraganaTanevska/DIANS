package mk.ukim.finki.dians.projectdians.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/aboutUs")
public class AboutUsController {
    @GetMapping
    public String getAboutUsPage(Model model) {
        model.addAttribute("bodyContent", "aboutUs");
        return "aboutUs";
        //tuka bese master-template
    }
}
