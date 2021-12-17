package mk.ukim.finki.dians.projectdians.service;

import mk.ukim.finki.dians.projectdians.model.Place;

import java.util.List;
import java.util.Optional;

public interface PlaceService {
    List<Place> findAll();
    Optional<Place> savePlace(String lat, String lon, String name, String website, String adress, String openingHours);
    void deletePlace(Long id);
    Optional<Place> findById(Long id);

}
