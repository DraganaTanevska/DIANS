//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package mk.ukim.finki.dians.projectdians.web;

import mk.ukim.finki.dians.projectdians.model.Taxi;
import mk.ukim.finki.dians.projectdians.service.TaxiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"/taxi"})
public class TaxiController {
    private final TaxiService taxiService;

    public TaxiController(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    @GetMapping({"/list-all"})
    public String getTaxisPage(@RequestHeader(name = "User-Agent",required = false) String user, @RequestParam(required = false) String error, @RequestParam(required = false) String name, Model model) {
        model.addAttribute("taxis", this.taxiService.findAll());
        return "listAllTaxis";
    }

    @GetMapping({"/edit-form/{id}"})
    public String getEditTaxiPage(@RequestParam(required = false) String error, @PathVariable Long id, Model model) {
        Taxi taxi=taxiService.findById(id);
        model.addAttribute("taxi", taxi);
        return "addNewTaxi";

    }

    @GetMapping({"/add-new"})
    public String addNewTaxiPage(@RequestHeader(name = "User-Agent",required = false) String user, @RequestParam(required = false) String error, @RequestParam(required = false) String name, Model model) {
        return "addNewTaxi";
    }

    @PostMapping({"/add"})
    public String postNewPlacePage(@RequestParam(required = false) Long id,@RequestHeader(name = "User-Agent",required = false) String user, @RequestParam(required = false) String error, @RequestParam(required = false) String phoneNumber, @RequestParam(required = false) String name, Model model) {
        if(id!=null)
        {
            taxiService.editTaxi(id,name,phoneNumber);
        return "redirect:/taxi/list-all";
        }
        this.taxiService.saveTaxi(name, phoneNumber);
        return "redirect:/taxi/list-all";
    }

    @GetMapping({"/delete/{id}"})
    public String deleteParking(@PathVariable Long id, @RequestHeader(name = "User-Agent",required = false) String user, @RequestParam(required = false) String error, @RequestParam(required = false) String name, Model model) {
        this.taxiService.deleteTaxi(id);
        return "redirect:/taxi/list-all";
    }

    @PostMapping("/sort")
    public String SortTaxi(@RequestParam(required = false) String sortType,Model model)
    {
        if(sortType.equals("Rating"))
        {
            model.addAttribute("taxis", this.taxiService.sortAllByRating());
            return "listAllTaxis";
        }
        else {
            model.addAttribute("taxis", this.taxiService.sortAllByName());
            return "listAllTaxis";
        }
    }
    @PostMapping("/search")
    public String searchTaxi(@RequestParam(required = false) String search, Model model){

        model.addAttribute("taxis",taxiService.findAllByNameContains(search));
        return "listAllTaxis";
    }
}
