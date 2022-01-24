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

    /**
     *
     * @param id - Finding the specific Parking to be edited
     * @return addNewPakring.html
     */
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
    /**
     *
     * @param id - if called from edit it will find the specific Parking with given id to be changed or not,otherwise its null
     * @param name - if called from edit it will find the specific Parking with given name to be changed or not,otherwise it will add new Parking with new name
     * @param lon - if called from edit it will find the specific Parking with given lon to be changed or not,otherwise it will add new Parking with new longitude
     * @param lat - if called from edit it will find the specific Parking with given lat to be changed or not,otherwise it will add new Parking with new latitude
     * @param website - if called from edit it will find the specific Parking with given website to be changed or not,otherwise it will add new Parking with new website
     * @param adress - if called from edit it will find the specific Parking with given adress to be changed or not,otherwise it will add new Parking with new adress
     * @param openingHours - if called from edit it will find the specific Parking with given openingHours to be changed or not,otherwise it will add new Parking with new opening hours
     * @return listAllParkings.html
     */
    @PostMapping({"/add"})
    public String postNewParkingPage(@RequestParam(required = false) Long id, @RequestHeader(name = "User-Agent", required = false) String user, @RequestParam(required = false) String website, @RequestParam(required = false) String error, @RequestParam(required = false) String lon, @RequestParam(required = false) String lat, @RequestParam(required = false) String name, @RequestParam(required = false) String adress, @RequestParam(required = false) String openingHours) {

        if (id != null) {
            parkingService.editParking(id, lat, lon, name, website, adress, openingHours);
            return "redirect:/parking/list-all";
        }
        this.parkingService.saveParking(lat, lon, name, website, adress, openingHours);
        return "redirect:/parking/list-all";
    }

    /**
     *
     * @param id - Finding the specific Pakring to be deleted
     * @return listAllParkings.html
     */
    @GetMapping({"/delete/{id}"})
    public String deleteParking(@PathVariable Long id) {
        this.parkingService.deleteParking(id);
        return "redirect:/parking/list-all";
    }

    /**
     * this function gets distance from user's location to a selected place
     *
     * @param id - the place to get distance to.
     * @param latitude  - user latitude.
     * @param longitude - user longitude.
     * @return listAllParkings.html
     * @throws IOException
     * @throws InterruptedException
     */

    @PostMapping("/distance")
    public String distance(@RequestParam(required = false) Long id,
                           @RequestParam(required = false) String latitude,
                           @RequestParam(required = false) String longitude,
                           Model model) throws IOException, InterruptedException {
        Parking place = parkingService.findById(id);
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
        model.addAttribute("parkings", this.parkingService.findALlParking());
        return "listAllParkings";
    }

}
