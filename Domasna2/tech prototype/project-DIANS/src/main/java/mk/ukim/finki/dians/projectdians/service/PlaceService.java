package mk.ukim.finki.dians.projectdians.service;

import mk.ukim.finki.dians.projectdians.model.Parking;
import mk.ukim.finki.dians.projectdians.model.Place;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface PlaceService {
    List<Place> findAll();
    Optional<Place> savePlace(String lat, String lon, String name, String website, String adress, String openingHours);
    void deletePlace(Long id);
    Place findById(Long id);
    //todo za site search query ama nema po sto...
    Optional<Place> editPlace(Long id, String lat, String lon, String name, String website, String adress, String openingHours);

    List<Place> findAllByNameContains(String name);
    List<Place> findAllByRatingAfter(double rating);
    List<Place> sortAllByName();
    List<Place> sortAllByRating();

}
