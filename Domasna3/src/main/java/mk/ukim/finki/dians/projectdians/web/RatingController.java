//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package mk.ukim.finki.dians.projectdians.web;

import mk.ukim.finki.dians.projectdians.service.PlaceTypeService;
import mk.ukim.finki.dians.projectdians.service.RatingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping({"/rating"})
public class RatingController {
    RatingService ratingService;
    PlaceTypeService placeTypeService;

    public RatingController(RatingService ratingService, PlaceTypeService placeTypeService) {
        this.ratingService = ratingService;
        this.placeTypeService = placeTypeService;

    }

    @GetMapping({"/add-new/{id}"})
    public String addNewPlacePage(@PathVariable Long id, @RequestHeader(name = "User-Agent", required = false) String user, @RequestParam(required = false) String error, @RequestParam(required = false) String name, Model model) {
        model.addAttribute("placeType", id);
        return "addNewRating";
    }

    @PostMapping({"/add"})
    public String postNewPlacePage(@RequestParam(required = false) int rating, HttpServletRequest req, @RequestParam(required = false) Long id, @RequestParam(required = false) String error, @RequestParam(required = false) String phoneNumber, @RequestParam(required = false) String name, Model model) {
        String username = req.getRemoteUser();
        this.ratingService.save(id, username, rating);
        return "redirect:/home";
    }
}
