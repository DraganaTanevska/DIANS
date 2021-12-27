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
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().GET()
                .header("accept","application/json")
                .uri(URI.create("https://maps.googleapis.com/maps/api/distancematrix/json?origins=41.1150384,41.1150384&destinations=41.1150384,41.1150384&key=YOUR_API_KEY"))
                .build();
       HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        StringBuilder builder = new StringBuilder();
        builder.append(response.body());
        String distance=builder.toString().split("\n")[8];
        String duration=builder.toString().split("\n")[12];
        String destination=builder.toString().split("\n")[1];
        String origin=builder.toString().split("\n")[2];
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
