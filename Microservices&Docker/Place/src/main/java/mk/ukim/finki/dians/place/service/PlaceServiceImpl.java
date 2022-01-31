package mk.ukim.finki.dians.place.service;

import mk.ukim.finki.dians.place.repository.PlaceRepository;
import mk.ukim.finki.dians.place.repository.PlaceTypeRepository;
import mk.ukim.finki.dians.place.model.Place;
import mk.ukim.finki.dians.place.model.PlaceType;
import mk.ukim.finki.dians.place.model.exceptions.PlaceIdNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository placeRepository;
    private final PlaceTypeRepository placeTypeRepository;

    public PlaceServiceImpl(PlaceRepository placeRepository, PlaceTypeRepository placeTypeRepository) {
        this.placeRepository = placeRepository;
        this.placeTypeRepository = placeTypeRepository;
    }

    @Override
    public List<Place> findAll() {
        return placeRepository.findAll();
    }

    @Override
    public Place savePlace(String lat, String lon, String name, String website, String adress, String openingHours) {
        Place place = new Place(lat, lon, name, website, adress, openingHours);
        return placeRepository.save(place);
    }

    @Override
    public Place deletePlace(Long id) {
        Place place=this.findById(id);
        placeRepository.deleteById(id);
        return place;
    }

    @Override
    public Place findById(Long id) {

        Place place = placeRepository.findById(id).orElseThrow(() -> new PlaceIdNotFoundException(id));
        PlaceType placeType = placeTypeRepository.findById(id).orElse(null);
        Place finalPlace = new Place(id, placeType.getName(), placeType.getNumberOfPeopleRating(), placeType.getFinalRating(), place.getLat(), place.getLon(), place.getWebsite(), place.getAddr_street(), place.getOpening_hours());
        return finalPlace;
    }

    @Override
    public Place editPlace(Long id, String lat, String lon, String name, String website, String adress, String openingHours) {
        Place place = this.findById(id);
        place.setAddr_street(adress);
        place.setLon(lon);
        place.setLat(lat);
        place.setWebsite(website);
        place.setOpening_hours(openingHours);
        place.setName(name);
        return placeRepository.save(place);
    }

    @Override
    public List<Place> findAllByNameContains(String name) {
        return placeRepository.findAllByNameContains(name);
    }

    @Override
    public List<Place> sortAllByName() {
        return placeRepository.findAllByOrderByNameAsc();
    }

    @Override
    public List<Place> sortAllByRating() {
        return placeRepository.findAllByOrderByFinalRatingAsc();
    }

}
