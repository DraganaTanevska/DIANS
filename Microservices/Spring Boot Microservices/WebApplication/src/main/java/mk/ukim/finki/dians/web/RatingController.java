//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package mk.ukim.finki.dians.web;

import mk.ukim.finki.dians.service.PlaceTypeService;
import mk.ukim.finki.dians.service.RatingService;
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
    public String addNewPlacePage(@PathVariable Long id,
                                  Model model) {
        model.addAttribute("placeType", id);
        return "addNewRating";
    }

    @PostMapping({"/add"})
    public String postNewPlacePage(@RequestParam(required = false) Integer rating,
                                   HttpServletRequest req,
                                   @RequestParam(required = false) Long id) {
        String username = req.getRemoteUser();
        this.ratingService.save(id, username, rating);
        return "redirect:/home";
    }
}
