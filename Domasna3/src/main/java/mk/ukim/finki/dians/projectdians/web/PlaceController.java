//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package mk.ukim.finki.dians.projectdians.web;

import mk.ukim.finki.dians.projectdians.model.Place;
import mk.ukim.finki.dians.projectdians.service.PlaceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"/place"})
public class PlaceController {
    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping({"/list-all"})
    public String getPlacesPage(@RequestHeader(name = "User-Agent",required = false) String user, @RequestParam(required = false) String error, @RequestParam(required = false) String name, Model model) {
        model.addAttribute("places", this.placeService.findAll());
        return "listAllPlaces";
    }

    @GetMapping({"/edit-form/{id}"})
    public String getEditPlacePage(@RequestParam(required = false) String error, @PathVariable Long id, Model model) {
        Place place=placeService.findById(id);
        model.addAttribute("place", place);
        return "addNewPlace";
    }

    @GetMapping({"/add-new"})
    public String addNewPlacePage(@RequestHeader(name = "User-Agent",required = false) String user, @RequestParam(required = false) String error, @RequestParam(required = false) String name, Model model) {
        return "addNewPlace";
    }

    @PostMapping({"/add"})
    public String postNewPlacePage(@RequestParam(required = false) Long id,@RequestHeader(name = "User-Agent",required = false) String user, @RequestParam(required = false) String name, @RequestParam(required = false) String error, @RequestParam(required = false) String lon, @RequestParam(required = false) String lat, @RequestParam(required = false) String website, @RequestParam(required = false) String adress, @RequestParam(required = false) String openingHours, Model model) {
        if(id!=null){
            this.placeService.editPlace(id,lat,lon,name,website,adress,openingHours);
            return  "redirect:/place/list-all";
        }
        this.placeService.savePlace(lat, lon, name, website, adress, openingHours);
        return "redirect:/place/list-all";

    }

    @GetMapping({"/delete/{id}"})
    public String deletePlace(@PathVariable Long id, @RequestHeader(name = "User-Agent",required = false) String user, @RequestParam(required = false) String error, @RequestParam(required = false) String name, Model model) {
        this.placeService.deletePlace(id);
        return "redirect:/place/list-all";
    }

    @PostMapping("/sort")
    public String SortPlace(@RequestParam(required = false) String sortType,Model model)
    {
        if(sortType.equals("Rating"))
        {
            model.addAttribute("places", this.placeService.sortAllByRating());
            return "listAllPlaces";
        }
        else {
            model.addAttribute("places", this.placeService.sortAllByName());
            return "listAllPlaces";
        }
    }
    @PostMapping("/search")
    public String searchPlace(@RequestParam(required = false) String search, Model model){

        model.addAttribute("places",placeService.findAllByNameContains(search));
        return "listAllPlaces";
    }
}
