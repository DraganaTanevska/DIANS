package mk.ukim.finki.dians.projectdians.service.impl;

import mk.ukim.finki.dians.projectdians.model.Parking;
import mk.ukim.finki.dians.projectdians.model.Place;
import mk.ukim.finki.dians.projectdians.model.PlaceType;
import mk.ukim.finki.dians.projectdians.repository.PlaceRepository;
import mk.ukim.finki.dians.projectdians.repository.PlaceTypeRepository;
import mk.ukim.finki.dians.projectdians.service.PlaceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository placeRepository;
    private final PlaceTypeRepository placeTypeRepository;

    public PlaceServiceImpl(PlaceRepository placeRepository, PlaceTypeRepository placeTypeRepository) {
        this.placeRepository = placeRepository;
        this.placeTypeRepository=placeTypeRepository;
    }

    @Override
    public List<Place> findAll() {
        return placeRepository.findAll();
    }

    @Override
    public Optional<Place> savePlace(String lat, String lon, String name, String website, String adress, String openingHours) {
        Place place=new Place(lat,lon,name,website,adress,openingHours);
        return Optional.of(placeRepository.save(place));
    }

    @Override
    public void deletePlace(Long id) {
        placeRepository.deleteById(id);
    }

    @Override
    public Place findById(Long id) {
        Place place=placeRepository.findById(id).orElse(null);
        PlaceType placeType=placeTypeRepository.findById(id).orElse(null);
        Place finalPlace=new Place(id,placeType.getName(),placeType.getNumberOfPeopleRating(),placeType.getFinalRating(),place.getLat(),place.getLon(),place.getWebsite(),place.getAddr_street(),place.getOpening_hours());
        return finalPlace;
    }
    @Override
    public Optional<Place> editPlace(Long id, String lat, String lon, String name, String website, String adress, String openingHours) {
        Place place=placeRepository.findById(id).orElse(null);
        place.setAddr_street(adress);
        place.setLon(lon);
        place.setLat(lat);
        place.setWebsite(website);
        place.setOpening_hours(openingHours);
        place.setName(name);
        return Optional.of(placeRepository.save(place));
    }

}
