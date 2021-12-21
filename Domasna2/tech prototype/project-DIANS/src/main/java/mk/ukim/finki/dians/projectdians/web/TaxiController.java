//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package mk.ukim.finki.dians.projectdians.web;

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
        model.addAttribute("taxi", this.taxiService.findById(id));
        return "redirect:/taxi/add";

    }

    @GetMapping({"/add-new"})
    public String addNewTaxiPage(@RequestHeader(name = "User-Agent",required = false) String user, @RequestParam(required = false) String error, @RequestParam(required = false) String name, Model model) {
        return "addNewTaxi";
    }

    @PostMapping({"/add"})
    public String postNewPlacePage(@RequestHeader(name = "User-Agent",required = false) String user, @RequestParam(required = false) String error, @RequestParam(required = false) String phoneNumber, @RequestParam(required = false) String name, Model model) {
        this.taxiService.saveTaxi(name, phoneNumber);
        return "redirect:/place/list-all";
    }

    @GetMapping({"/delete/{id}"})
    public String deleteParking(@PathVariable Long id, @RequestHeader(name = "User-Agent",required = false) String user, @RequestParam(required = false) String error, @RequestParam(required = false) String name, Model model) {
        this.taxiService.deleteTaxi(id);
        return "redirect:/taxi/list-all";
    }
}
