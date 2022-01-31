//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package mk.ukim.finki.dians.rating.web;

import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.dians.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping({"/ratingrest"})
public class RatingController {

    @Autowired
    private RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;

    }

    @PostMapping({"/save/{send}"})
    public String postNewPlacePage(@PathVariable String send) {
        Long id=Long.valueOf(send.split("\\+")[0]);

        String username = send.split("\\+")[1];
        Integer rating = Integer.valueOf(send.split("\\+")[2]);
        this.ratingService.save(id, username, rating);
        return username;
    }
}
