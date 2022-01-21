//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package mk.ukim.finki.dians.projectdians.web;

import mk.ukim.finki.dians.projectdians.model.Place;
import mk.ukim.finki.dians.projectdians.service.PlaceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Controller
@RequestMapping({"/place"})
public class PlaceController {
    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }


    /**
     * @param search   - search parameter, can be null. IN CASE IT IS NULL sortType is checked.
     * @param sortType - sorting parameter, can be null. IN CASE IT IS NULL all taxis are shown.
     * @param model
     * @return listAllParkings.html
     */
    @GetMapping({"/list-all"})
    public String getPlacesPage(@RequestParam(required = false) String search,
                                @RequestParam(required = false) String sortType,
                                Model model) {

        if (search != null) {
            model.addAttribute("places", placeService.findAllByNameContains(search));
        } else if (sortType != null) {
            if (sortType.equals("Rating")) {
                model.addAttribute("places", this.placeService.sortAllByRating());
            } else if (sortType.equals("Name")) {
                model.addAttribute("places", this.placeService.sortAllByName());
            }
        } else {
            model.addAttribute("places", this.placeService.findAll());
        }
        return "listAllPlaces";
    }

    @GetMapping({"/edit-form/{id}"})
    public String getEditPlacePage(@PathVariable Long id,
                                   Model model) {
        Place place = placeService.findById(id);
        model.addAttribute("place", place);
        return "addNewPlace";
    }

    @GetMapping({"/add-new"})
    public String addNewPlacePage() {
        return "addNewPlace";
    }

    @PostMapping({"/add"})
    public String postNewPlacePage(@RequestParam(required = false) Long id,
                                   @RequestParam(required = false) String name,
                                   @RequestParam(required = false) String lon,
                                   @RequestParam(required = false) String lat,
                                   @RequestParam(required = false) String website,
                                   @RequestParam(required = false) String adress,
                                   @RequestParam(required = false) String openingHours,
                                   Model model) {
        if (id != null) {
            this.placeService.editPlace(id, lat, lon, name, website, adress, openingHours);
        }
        this.placeService.savePlace(lat, lon, name, website, adress, openingHours);
        return "redirect:/place/list-all";

    }

    @GetMapping({"/delete/{id}"})
    public String deletePlace(@PathVariable Long id) {
        this.placeService.deletePlace(id);
        return "redirect:/place/list-all";
    }

    /**
     * this function gets distance from user's location to a selected place
     *
     * @param idPlace   - the place to get distance to.
     * @param latitude  - user latitude.
     * @param longitude - user longitude.
     * @return listAllPlaces.html
     * @throws IOException
     * @throws InterruptedException
     */

    @PostMapping("/distance")
    public String distance(@RequestParam(required = false) Long idPlace,
                           @RequestParam(required = false) String latitude,
                           @RequestParam(required = false) String longitude, Model model) throws IOException, InterruptedException {
        Place place = placeService.findById(idPlace);
        String DestinationLat = place.getLat();
        String DestinationLon = place.getLon();
        String latOrigin = latitude;
        String lonOrigin = longitude;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().GET()
                .header("accept", "application/json")
                .uri(URI.create("https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + latOrigin + "," + longitude + "&destinations=" + DestinationLat + "," + DestinationLon + "&key=AIzaSyD6KknbJQDT4mxXyb676yAzDsN5AMLAfrU"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        StringBuilder builder = new StringBuilder();
        builder.append(response.body());
        String distance = builder.toString().split("\n")[8];
        String duration = builder.toString().split("\n")[12];
        String destination = builder.toString().split("\n")[1];
        String origin = builder.toString().split("\n")[2];
        model.addAttribute("distance", distance);
        model.addAttribute("origin", origin);
        model.addAttribute("duration", duration);
        model.addAttribute("destination", destination);
        model.addAttribute("places", this.placeService.findAll());
        return "listAllPlaces";
    }
}
