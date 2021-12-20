package mk.ukim.finki.dians.projectdians.web;

import mk.ukim.finki.dians.projectdians.service.ParkingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/parking")
public class ParkingController {
    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }
    @GetMapping("/list-all")
    public String getParkingsPage(@RequestHeader(name="User-Agent", required = false) String user, @RequestParam(required = false) String error, @RequestParam(required = false) String name, Model model){
        model.addAttribute("parkings",parkingService.findALlParking());
        return "listAllParkings";
        //raboti stranata raboti logikata
    }
    @GetMapping("/edit-form/{id}")
    public String getEditParkingPage(@RequestParam (required = false) String error,@PathVariable Long id, Model model)
    {
        //IMPLEMENT
        model.addAttribute("parking",parkingService.findById(id));
        return "redirect:/parking/add";
        //ne mozes da pravis redirect od tuka se gubat informacii moras biznis logikata xD da e tuka inc raboti linkot
    }
    @GetMapping("/add-new")
    public String addNewParkingPage(@RequestHeader(name="User-Agent", required = false) String user, @RequestParam(required = false) String error, @RequestParam(required = false) String name, Model model){
        return "addNewParking";
        //raboti stranata
    }
    @PostMapping("/add")
    public String postNewParkingPage(@RequestHeader(name="User-Agent", required = false) String user,@RequestParam(required = false) String website, @RequestParam(required = false) String error,@RequestParam(required = false) String lon, @RequestParam(required = false) String lat, @RequestParam(required = false) String name, @RequestParam(required = false) String adress, @RequestParam(required = false) String openingHours, Model model)
    {
        //todo da raboti i za edit
        parkingService.saveParking(lat,lon,name,website,adress,openingHours);
        return "redirect:/parking/list-all";
        //raboti od add new parking a za edit moras gore da smenis toa sto ti pisav :D
    }
    @GetMapping("/delete/{id}")
    public String deleteParking(@PathVariable Long id,@RequestHeader(name="User-Agent", required = false) String user, @RequestParam(required = false) String error, @RequestParam(required = false) String name, Model model){
        parkingService.deleteParking(id);
        return "redirect:/parking/list-all";
        //raboti stranata raboti funkcionalnosta
    }
}
