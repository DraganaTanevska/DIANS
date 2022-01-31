//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package mk.ukim.finki.dians.place.web;

import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.dians.place.model.Place;
import mk.ukim.finki.dians.place.service.PlaceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping({"/placerest"})
public class PlaceController {
    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }


    /**
     * @param sort - sorting parameter, can be null. IN CASE IT IS NULL all taxis are shown.

     * @return list of places
     */
    @GetMapping({"/list-all/{sort}"})
    public List<Place> getPlacesPage(@PathVariable String sort) {

        List<Place> places=new ArrayList<>();
         if (sort != null) {
            if (sort.equals("rating")) {
                places=this.placeService.sortAllByRating();
            } else if (sort.equals("name")) {
                places=this.placeService.sortAllByName();
            }
            else {
                places=this.placeService.findAll();
        }
        }
        return places;
    }
    @GetMapping("/search/{search}")
    public List<Place> searchPlaces(@PathVariable String search){
        List <Place> places=placeService.findAllByNameContains(search);
        return places;
    }

    /**
     *
     * send - all params
     * @return place
     */
    @GetMapping({"/add/{send}"})
    public Place postNewPlacePage(@PathVariable String send) {
        String [] strings=send.split("\\+");
        if (strings.length == 7) {
            Long id=Long.valueOf(strings[0]);
            String lat=strings[1];
            String lon=strings[2];
            String name=strings[3];
            String website=strings[4];
            String adress=strings[5];
            String openingHours=strings[6];
            return this.placeService.editPlace(id, lat, lon, name, website, adress, openingHours);
        }
        String lat=strings[0];
        String lon=strings[1];
        String name=strings[2];
        String website=strings[3];
        String adress=strings[4];
        String openingHours=strings[5];
        return this.placeService.savePlace(lat, lon, name, website, adress, openingHours);
    }

    /**
     *
     * @param id - Finding the specific Place to be deleted
     * @return place
     */
    @GetMapping({"/delete/{id}"})
    public Place deletePlace(@PathVariable Long id) {
        return this.placeService.deletePlace(id);
    }

    @GetMapping({"/find/{id}"})
    public Place findById(@PathVariable Long id){
        return this.placeService.findById(id);
    }

}
