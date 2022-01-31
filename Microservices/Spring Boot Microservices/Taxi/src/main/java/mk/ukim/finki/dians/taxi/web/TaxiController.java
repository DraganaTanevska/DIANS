//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package mk.ukim.finki.dians.taxi.web;

import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.dians.taxi.model.Taxi;
import mk.ukim.finki.dians.taxi.service.TaxiService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping({"/taxirest"})
public class TaxiController {
    private final TaxiService taxiService;

    public TaxiController(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    /**
     * @param sort - sorting parameter, can be null. IN CASE IT IS NULL all taxis are shown.

     * @return listAllTaxis.html
     */
    @GetMapping({"/list-all/{sort}"})
    public List<Taxi> getTaxisPage(@PathVariable String sort) {

        List<Taxi> taxis=new ArrayList<>();
         if (sort != null) {
            if (sort.equals("rating")) {
                taxis=taxiService.sortAllByRating();
            } else if (sort.equals("name")) {
                taxis=this.taxiService.sortAllByName();
            }
            else {
                taxis=this.taxiService.findAll();
            }
        }
        return taxis;
    }

    /**
     *
     * @param search   - search parameter, can be null. IN CASE IT IS NULL sortType is checked.
     * @return
     */
    @GetMapping("/search/{search}")
    public List<Taxi> getTaxiSearch(@PathVariable String search){
        List<Taxi> taxis=new ArrayList<>();
        taxis=this.taxiService.findAllByNameContains(search);
        return taxis;
    }

    @GetMapping({"/add/{send}"})
    public Taxi postNewPlacePage(@PathVariable String send) {
        String []string=send.split("\\+");

        if (string.length == 3) {
            Long id=Long.valueOf(string[0]);
            String name=string[1];
            String phoneNumber=string[2];
            return taxiService.editTaxi(id, name, phoneNumber);

        }
        String name=string[0];
        String phoneNumber=string[1];
        return this.taxiService.saveTaxi(name, phoneNumber);
    }
    /**
     *
     * @param id - Finding the specific Taxi to be deleted
     * @return listAllTaxis.html
     */
    @GetMapping({"/delete/{id}"})
    public Taxi deleteParking(@PathVariable Long id) {
        return this.taxiService.deleteTaxi(id);
    }
    @GetMapping({"/find/{id}"})
    public Taxi findById(@PathVariable Long id){
        return this.taxiService.findById(id);
    }
}
