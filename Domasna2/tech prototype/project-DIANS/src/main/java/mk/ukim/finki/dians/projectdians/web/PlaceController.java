package mk.ukim.finki.dians.projectdians.web;

import mk.ukim.finki.dians.projectdians.service.PlaceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/place")
public class PlaceController {

    private final PlaceService placeService;
    public PlaceController(PlaceService placeService){
        this.placeService=placeService;
    }
    @GetMapping("/list-all")
    public String getPlacesPage(@RequestHeader(name="User-Agent", required = false) String user, @RequestParam(required = false) String error, @RequestParam(required = false) String name, Model model){
        return "listAllPlaces";
    }
    @GetMapping("/add-new")
    public String addNewPlacePage(@RequestHeader(name="User-Agent", required = false) String user, @RequestParam(required = false) String error, @RequestParam(required = false) String name, Model model){
        return "addNewPlace";
    }
}
