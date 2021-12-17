package mk.ukim.finki.dians.projectdians.service.impl;

import mk.ukim.finki.dians.projectdians.model.Place;
import mk.ukim.finki.dians.projectdians.repository.PlaceRepository;
import mk.ukim.finki.dians.projectdians.service.PlaceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceServiceImpl implements PlaceService {
    public PlaceRepository placeRepository;

    public PlaceServiceImpl(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
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
        placeRepository.delete(placeRepository.getById(id));
    }

    @Override
    public Optional<Place> findById(Long id) {
        return Optional.of(placeRepository.getById(id));
    }
}
