package mk.ukim.finki.dians.service;

import mk.ukim.finki.dians.model.Place;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PlaceService {


    List<Place> findAll();

    Place savePlace(String lat, String lon, String name, String website, String adress, String openingHours);

    void deletePlace(Long id);

    Place findById(Long id);

    Optional<Place> editPlace(Long id, String lat, String lon, String name, String website, String adress, String openingHours);

    /**
     * this function should implement the search function
     *
     * @return List<Places>
     */
    List<Place> findAllByNameContains(String name);

    /**
     * this function should implement the sort function by Name in ascending order
     *
     * @return List<Places>
     */
    List<Place> sortAllByName();

    /**
     * this function should implement the sort function by Rating in ascending order
     *
     * @return List<Places>
     */
    List<Place> sortAllByRating();

}
