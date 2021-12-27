package mk.ukim.finki.dians.projectdians.service;

import mk.ukim.finki.dians.projectdians.model.Parking;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface ParkingService {

    List<Parking> findALlParking();
    Optional<Parking> saveParking(String lat, String lon, String name, String website, String adress, String openingHours);
    void deleteParking(Long id);
    Parking findById(Long id);
    Optional<Parking> editParking(Long id,String lat, String lon, String name, String website, String adress, String openingHours);
    List<Parking> findAllByNameContains(String name);
    List<Parking> findAllByRatingAfter(double rating);
    List<Parking> sortAllByName();
    List<Parking> sortAllByRating();

}
