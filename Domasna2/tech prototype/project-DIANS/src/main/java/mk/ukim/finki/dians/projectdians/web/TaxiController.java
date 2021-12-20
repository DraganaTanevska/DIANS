package mk.ukim.finki.dians.projectdians.web;

import mk.ukim.finki.dians.projectdians.service.TaxiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/taxi")
public class TaxiController {
    private final TaxiService taxiService;

    public TaxiController(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    @GetMapping("/list-all")
    public String getTaxisPage(@RequestHeader(name="User-Agent", required = false) String user, @RequestParam(required = false) String error, @RequestParam(required = false) String name, Model model){
        model.addAttribute("taxis",taxiService.findAll());
        return "listAllTaxis";
        //raboti se,NAUCI DA KUCAS HTML!!
    }
    @GetMapping("/edit-form/{id}")
    public String getEdittaxiPage(@RequestParam (required = false) String error,@PathVariable Long id, Model model)
    {
        //IMPLEMENT
        model.addAttribute("taxi",taxiService.findById(id));
        return "redirect:/taxi/add";
        //istata utka od parking controller ke ja popravis
    }
    @GetMapping("/add-new")
    public String addNewTaxiPage(@RequestHeader(name="User-Agent", required = false) String user, @RequestParam(required = false) String error, @RequestParam(required = false) String name, Model model){
        return "addNewTaxi";
        //raboti
    }

    @PostMapping("/add")
    public String postNewPlacePage(@RequestHeader(name="User-Agent", required = false) String user,@RequestParam Long id, @RequestParam(required = false) String error,@RequestParam(required = false) String phoneNumber, @RequestParam(required = false) String name,Model model)
    {
        //TODO da raboti i za edit
        taxiService.saveTaxi(name,phoneNumber);
        return "redirect:/place/list-all";
        //BAD REQUEST dava radi ova RequestParam Long id ne sfakam so sakas da napravis so toa
    }
    @GetMapping("/delete/{id}")
    public String deleteParking(@PathVariable Long id, @RequestHeader(name="User-Agent", required = false) String user, @RequestParam(required = false) String error, @RequestParam(required = false) String name, Model model){

        taxiService.deleteTaxi(id);
        return "redirect:/taxi/list-all";
        //raboti
    }
}