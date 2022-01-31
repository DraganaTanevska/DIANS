package mk.ukim.finki.dians.service.impl;

import mk.ukim.finki.dians.model.Place;
import mk.ukim.finki.dians.repository.PlaceRepository;
import mk.ukim.finki.dians.repository.PlaceTypeRepository;
import mk.ukim.finki.dians.service.PlaceService;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository placeRepository;
    private final PlaceTypeRepository placeTypeRepository;
    private final RestTemplate restTemplate;

    private final PasswordEncoder passwordEncoder;
    public PlaceServiceImpl(PlaceRepository placeRepository, PlaceTypeRepository placeTypeRepository, RestTemplate restTemplate, PasswordEncoder passwordEncoder) {
        this.placeRepository = placeRepository;
        this.placeTypeRepository = placeTypeRepository;
        this.restTemplate = restTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Place> findAll() {

        String sort="nothing";
        Place[] places = restTemplate.getForObject("http://localhost:9191/placerest/list-all/"+sort,Place[].class);
        List<Place> placeList = Arrays.asList(places);
        return placeList;
    }

    @Override
    public Place savePlace(String lat, String lon, String name, String website, String adress, String openingHours) {
        String send=lat+'+'+lon+'+'+name+'+'+website+'+'+adress+'+'+openingHours;
        Place place = restTemplate.getForObject("http://localhost:9191/placerest/add/"+send,Place.class);
        return place;
    }

    @Override
    public void deletePlace(Long id) {
        Place place = restTemplate.getForObject("http://localhost:9191/placerest/delete/"+id,Place.class);
    }

    @Override
    public Place findById(Long id) {
        //napraj vo restot vakva funkcija
        Place place = restTemplate.getForObject("http://localhost:9191/placerest/find/"+id,Place.class);
        return place;
    }

    @Override
    public Optional<Place> editPlace(Long id, String lat, String lon, String name, String website, String adress, String openingHours) {

        String send=id+'+'+lat+'+'+lon+'+'+name+'+'+website+'+'+adress+'+'+openingHours;
        Place place = restTemplate.getForObject("http://localhost:9191/placerest/add/"+send,Place.class);
        return Optional.of(place);
    }

    @Override
    public List<Place> findAllByNameContains(String search) {
        //dodaj search vo restot
        Place[] places = restTemplate.getForObject("http://localhost:9191/placerest/search/"+search,Place[].class);
        List<Place> placeList = Arrays.asList(places);
        return placeList;
    }

    @Override
    public List<Place> sortAllByName() {
        String sort="name";
        Place[] places = restTemplate.getForObject("http://localhost:9191/placerest/list-all/"+sort,Place[].class);
        List<Place> placeList = Arrays.asList(places);
        return placeList;
    }

    @Override
    public List<Place> sortAllByRating() {
        String sort="rating";
        Place[] places = restTemplate.getForObject("http://localhost:9191/placerest/list-all/"+sort,Place[].class);
        List<Place> placeList = Arrays.asList(places);
        return placeList;
    }

}
