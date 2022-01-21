//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package mk.ukim.finki.dians.projectdians.web;

import mk.ukim.finki.dians.projectdians.model.Parking;
import mk.ukim.finki.dians.projectdians.service.ParkingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Controller
@RequestMapping({"/parking"})
public class ParkingController {
    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }


    /**
     * @param search   - search parameter, can be null. IN CASE IT IS NULL sortType is checked.
     * @param sortType - sorting parameter, can be null. IN CASE IT IS NULL all taxis are shown.
     * @param model
     * @return listAllParkings.html
     */

    @GetMapping({"/list-all"})
    public String getParkingsPage(@RequestParam(required = false) String sortType,
                                  @RequestParam(required = false) String search,
                                  Model model) {
        if (search != null) {
            model.addAttribute("parkings", parkingService.findAllByNameContains(search));
        } else if (sortType != null) {
            if (sortType.equals("Rating")) {
                model.addAttribute("parkings", this.parkingService.sortAllByRating());
            } else if (sortType.equals("Name")) {
                model.addAttribute("parkings", this.parkingService.sortAllByName());
            }
        } else {
            model.addAttribute("parkings", this.parkingService.findALlParking());
        }
        return "listAllParkings";
    }

    @GetMapping({"/edit-form/{id}"})
    public String getEditParkingPage(@PathVariable Long id,
                                     Model model) {
        Parking parking = parkingService.findById(id);
        model.addAttribute("parking", parking);
        return "addNewParking";
    }

    @GetMapping({"/add-new"})
    public String addNewParkingPage() {
        return "addNewParking";

    }

    @PostMapping({"/add"})
    public String postNewParkingPage(@RequestParam(required = false) Long id, @RequestHeader(name = "User-Agent", required = false) String user, @RequestParam(required = false) String website, @RequestParam(required = false) String error, @RequestParam(required = false) String lon, @RequestParam(required = false) String lat, @RequestParam(required = false) String name, @RequestParam(required = false) String adress, @RequestParam(required = false) String openingHours, Model model) {

        if (id != null) {
            parkingService.editParking(id, lat, lon, name, website, adress, openingHours);
            return "redirect:/parking/list-all";
        }
        this.parkingService.saveParking(lat, lon, name, website, adress, openingHours);
        return "redirect:/parking/list-all";
    }

    @GetMapping({"/delete/{id}"})
    public String deleteParking(@PathVariable Long id, @RequestHeader(name = "User-Agent", required = false) String user, @RequestParam(required = false) String error, @RequestParam(required = false) String name, Model model) {
        this.parkingService.deleteParking(id);
        return "redirect:/parking/list-all";
    }

    /**
     * this function gets distance from user's location to a selected place
     *
     * @param idParking - the place to get distance to.
     * @param latitude  - user latitude.
     * @param longitude - user longitude.
     * @return listAllPlaces.html
     * @throws IOException
     * @throws InterruptedException
     */

    @PostMapping("/distance")
    public String distance(@RequestParam(required = false) Long idParking,
                           @RequestParam(required = false) String latitude,
                           @RequestParam(required = false) String longitude,
                           Model model) throws IOException, InterruptedException {
        Parking place = parkingService.findById(idParking);
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
        model.addAttribute("places", this.parkingService.findALlParking());
        return "listAllPlaces";
    }

}
