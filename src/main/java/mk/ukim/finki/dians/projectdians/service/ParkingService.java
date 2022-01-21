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

    Optional<Parking> editParking(Long id, String lat, String lon, String name, String website, String adress, String openingHours);

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
