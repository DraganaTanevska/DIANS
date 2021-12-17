package mk.ukim.finki.dians.projectdians.service;

import mk.ukim.finki.dians.projectdians.model.Parking;

import java.util.List;
import java.util.Optional;

public interface ParkingService {

    List<Parking> findALlParking();
    Optional<Parking> saveParking(String lat, String lon, String name, String website, String adress, String openingHours);
    void deleteParking(Long id);
    Optional<Parking> findById(Long id);

}
