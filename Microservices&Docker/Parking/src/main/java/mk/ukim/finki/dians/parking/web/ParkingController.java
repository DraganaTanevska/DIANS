//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package mk.ukim.finki.dians.parking.web;

import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.dians.parking.model.Parking;
import mk.ukim.finki.dians.parking.service.ParkingService;
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

@RestController
@Slf4j
@RequestMapping({"/parkingrest"})
public class ParkingController {
    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }


    /**
     * @param sort - sorting parameter, can be null. IN CASE IT IS NULL all taxis are shown.

     * @return list of places
     */
    @GetMapping({"/list-all/{sort}"})
    public List<Parking> getParkingsPage(@PathVariable String sort) {

        List<Parking> places=new ArrayList<>();
        if (sort != null) {
            if (sort.equals("rating")) {
                places=this.parkingService.sortAllByRating();
            } else if (sort.equals("name")) {
                places=this.parkingService.sortAllByName();
            }
            else {
                places=this.parkingService.findALlParking();
            }
        }
        return places;
    }
    @GetMapping("/search/{search}")
    public List<Parking> searchParkings(@PathVariable String search){
        List <Parking> places=parkingService.findAllByNameContains(search);
        return places;
    }

    /**
     *
     * send - all params
     * @return place
     */
    @PostMapping({"/add/{send}"})
    public Parking postNewParkingPage(@PathVariable String send) {
        String [] strings=send.split("\\+");
        if (strings.length == 7) {
            Long id=Long.valueOf(strings[0]);
            String lat=strings[1];
            String lon=strings[2];
            String name=strings[3];
            String website=strings[4];
            String adress=strings[5];
            String openingHours=strings[6];
            return this.parkingService.editParking(id, lat, lon, name, website, adress, openingHours);
        }
        String lat=strings[0];
        String lon=strings[1];
        String name=strings[2];
        String website=strings[3];
        String adress=strings[4];
        String openingHours=strings[5];
        return this.parkingService.saveParking(lat, lon, name, website, adress, openingHours);
    }

    /**
     *
     * @param id - Finding the specific Taxi to be deleted
     * @return place
     */
    @GetMapping({"/delete/{id}"})
    public Parking deleteParking(@PathVariable Long id) {
        return this.parkingService.deleteParking(id);
    }
    @GetMapping({"/find/{id}"})
    public Parking findById(@PathVariable Long id){
        return this.parkingService.findById(id);
    }
}
