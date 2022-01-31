package mk.ukim.finki.dians.service.impl;

import mk.ukim.finki.dians.model.Taxi;
import mk.ukim.finki.dians.repository.PlaceTypeRepository;
import mk.ukim.finki.dians.repository.TaxiRepository;
import mk.ukim.finki.dians.service.TaxiService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TaxiServiceImpl implements TaxiService {

    private final TaxiRepository taxiRepository;

    private final PlaceTypeRepository placeTypeRepository;
    private final RestTemplate restTemplate;

    public TaxiServiceImpl(TaxiRepository taxiRepository, PlaceTypeRepository placeTypeRepository, RestTemplate restTemplate) {
        this.taxiRepository = taxiRepository;
        this.placeTypeRepository = placeTypeRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Taxi> findAll() {
        String sort="name";
        Taxi[] taxi = restTemplate.getForObject("http://localhost:9191/taxirest/list-all/"+sort,Taxi[].class);
        List<Taxi> taxiList = Arrays.asList(taxi);
        return taxiList;
    }

    @Override
    public Taxi saveTaxi(String name, String phoneNumber) {
        String send=name+'+'+phoneNumber;
        Taxi taxi = restTemplate.getForObject("http://localhost:9191/taxirest/add/"+send,Taxi.class);
        return taxi;
    }

    @Override
    public void deleteTaxi(Long id) {
        Taxi taxi = restTemplate.getForObject("http://localhost:9191/taxirest/delete/"+id,Taxi.class);
    }

    @Override
    public Taxi findById(Long id) {
        //napraj vo restot vakva funkcija
        Taxi taxi = restTemplate.getForObject("http://localhost:9191/taxirest/find/"+id,Taxi.class);
        return taxi;
    }

    @Override
    public Optional<Taxi> editTaxi(Long id, String name, String phoneNumber) {
        String send=id+'+'+name+'+'+phoneNumber;
        Taxi taxi = restTemplate.getForObject("http://localhost:9191/taxirest/add/"+send,Taxi.class);
        return Optional.of(taxi);
    }

    @Override
    public List<Taxi> findAllByNameContains(String search) {
        //dodaj /search vo restot
        Taxi[] taxi = restTemplate.getForObject("http://localhost:9191/taxirest/search/"+search,Taxi[].class);
        List<Taxi> taxiList = Arrays.asList(taxi);
        return taxiList;
    }

    @Override
    public List<Taxi> sortAllByName() {
        String sort="name";
        Taxi[] taxi = restTemplate.getForObject("http://localhost:9191/taxirest/list-all/"+sort,Taxi[].class);
        List<Taxi> taxiList = Arrays.asList(taxi);
        return taxiList;
    }

    @Override
    public List<Taxi> sortAllByRating() {
        String sort="rating";
        Taxi[] taxi = restTemplate.getForObject("http://localhost:9191/taxirest/list-all"+sort,Taxi[].class);
        List<Taxi> taxiList = Arrays.asList(taxi);
        return taxiList;
    }
}
