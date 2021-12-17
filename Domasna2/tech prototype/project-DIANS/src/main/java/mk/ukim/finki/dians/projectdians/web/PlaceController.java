package mk.ukim.finki.dians.projectdians.web;

import mk.ukim.finki.dians.projectdians.service.PlaceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/place")
public class PlaceController {

    private final PlaceService placeService;
    public PlaceController(PlaceService placeService){
        this.placeService=placeService;
    }
    @GetMapping("/list-all")
    public String getPlacesPage(@RequestHeader(name="User-Agent", required = false) String user, @RequestParam(required = false) String error, @RequestParam(required = false) String name, Model model){
        model.addAttribute("places",placeService.findAll());
        return "listAllPlaces";
    }
    @GetMapping("/edit-form/{id}")
    public String getEditPlacePage(@RequestParam (required = false) String error,@PathVariable Long id, Model model)
    {
        //IMPLEMENT
        model.addAttribute("place",placeService.findById(id));
        return "redirect:/place/add";
    }
    @GetMapping("/add-new")
    public String addNewPlacePage(@RequestHeader(name="User-Agent", required = false) String user, @RequestParam(required = false) String error, @RequestParam(required = false) String name, Model model){
        return "addNewPlace";
    }
    @PostMapping("/add")
    public String postNewPlacePage(@RequestHeader(name="User-Agent", required = false) String user,@RequestParam Long id, @RequestParam(required = false) String website, @RequestParam(required = false) String error,@RequestParam(required = false) String lon, @RequestParam(required = false) String lat, @RequestParam(required = false) String name, @RequestParam(required = false) String adress, @RequestParam(required = false) String openingHours, Model model)
    {
        //todo da raboti i za edit
        placeService.savePlace(lat,lon,name,website,adress,openingHours);
        return "redirect:/place/list-all";
    }

    @GetMapping("/delete/{id}")
    public String deletePlace(@PathVariable Long id,@RequestHeader(name="User-Agent", required = false) String user, @RequestParam(required = false) String error, @RequestParam(required = false) String name, Model model){

        placeService.deletePlace(id);
        return "redirect:/place/list-all";
    }

}
