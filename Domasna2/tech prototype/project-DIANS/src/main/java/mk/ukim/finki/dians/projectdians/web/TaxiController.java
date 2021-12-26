package mk.ukim.finki.dians.projectdians.web;

import mk.ukim.finki.dians.projectdians.service.TaxiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/taxi")
public class TaxiController {
    private final TaxiService taxiService;

    public TaxiController(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    @GetMapping("/list-all")
    public String getTaxisPage(@RequestHeader(name="User-Agent", required = false) String user, @RequestParam(required = false) String error, @RequestParam(required = false) String name, Model model){
        return "listAllTaxis";
    }
    @GetMapping("/add-new")
    public String addNewTaxiPage(@RequestHeader(name="User-Agent", required = false) String user, @RequestParam(required = false) String error, @RequestParam(required = false) String name, Model model){
        return "addNewTaxi";
    }
}