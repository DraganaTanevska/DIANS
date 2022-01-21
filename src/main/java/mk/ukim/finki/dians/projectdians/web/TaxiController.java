//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package mk.ukim.finki.dians.projectdians.web;

import mk.ukim.finki.dians.projectdians.model.Taxi;
import mk.ukim.finki.dians.projectdians.service.TaxiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({"/taxi"})
public class TaxiController {
    private final TaxiService taxiService;

    public TaxiController(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    /**
     * @param search   - search parameter, can be null. IN CASE IT IS NULL sortType is checked.
     * @param sortType - sorting parameter, can be null. IN CASE IT IS NULL all taxis are shown.
     * @param model
     * @return listAllTaxis.html
     */
    @GetMapping({"/list-all"})
    public String getTaxisPage(@RequestParam(required = false) String sortType,
                               @RequestParam(required = false) String search,
                               Model model) {

        if (search != null) {
            model.addAttribute("taxis", taxiService.findAllByNameContains(search));

        } else if (sortType != null) {
            if (sortType.equals("Rating")) {
                model.addAttribute("taxis", this.taxiService.sortAllByRating());
            } else if (sortType.equals("Name")) {
                model.addAttribute("taxis", this.taxiService.sortAllByName());
            }
        } else {
            model.addAttribute("taxis", this.taxiService.findAll());
        }
        return "listAllTaxis";
    }

    @GetMapping({"/edit-form/{id}"})
    public String getEditTaxiPage(@PathVariable Long id,
                                  Model model) {
        Taxi taxi = taxiService.findById(id);
        model.addAttribute("taxi", taxi);
        return "addNewTaxi";

    }

    @GetMapping({"/add-new"})
    public String addNewTaxiPage() {
        return "addNewTaxi";
    }

    @PostMapping({"/add"})
    public String postNewPlacePage(@RequestParam(required = false) Long id,
                                   @RequestParam(required = false) String phoneNumber,
                                   @RequestParam(required = false) String name,
                                   Model model) {
        if (id != null) {
            taxiService.editTaxi(id, name, phoneNumber);
            return "redirect:/taxi/list-all";
        }
        this.taxiService.saveTaxi(name, phoneNumber);
        return "redirect:/taxi/list-all";
    }

    @GetMapping({"/delete/{id}"})
    public String deleteParking(@PathVariable Long id) {
        this.taxiService.deleteTaxi(id);
        return "redirect:/taxi/list-all";
    }

}
