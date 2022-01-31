package mk.ukim.finki.dians.parking.service;

import mk.ukim.finki.dians.parking.model.Parking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParkingService {

    List<Parking> findALlParking();

    Parking saveParking(String lat, String lon, String name, String website, String adress, String openingHours);

    Parking deleteParking(Long id);

    Parking findById(Long id);

    Parking editParking(Long id, String lat, String lon, String name, String website, String adress, String openingHours);

    /**
     * this function should implement the search function
     * the function should ignore cases
     *
     * @return List<Places>
     */
    List<Parking> findAllByNameContains(String name);

    /**
     * this function should implement the sort function by Name in ascending order
     *
     * @return List<Places>
     */
    List<Parking> sortAllByName();

    /**
     * this function should implement the sort function by Rating in ascending order
     *
     * @return List<Parking>
     */
    List<Parking> sortAllByRating();

}
