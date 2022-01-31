package mk.ukim.finki.dians.service.impl;

import mk.ukim.finki.dians.model.Parking;
import mk.ukim.finki.dians.service.ParkingService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingServiceImpl implements ParkingService {
    private final RestTemplate restTemplate;

    public ParkingServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Parking> findAllParking() {
        String sort="nothing";
        Parking[] parkings = restTemplate.getForObject("http://localhost:9191/parkingrest/list-all/"+sort,Parking[].class);
        List<Parking> parkingList = Arrays.asList(parkings);
        return parkingList;
    }

    @Override
    public Parking saveParking(String lat, String lon, String name, String website, String adress, String openingHours) {
        String send=lat+'+'+lon+'+'+name+'+'+website+'+'+adress+'+'+openingHours;
        Parking parking = restTemplate.getForObject("http://localhost:9191/parkingrest/add/"+send,Parking.class);
        return parking;
    }

    @Override
    public void deleteParking(Long id) {
        Parking parking = restTemplate.getForObject("http://localhost:9191/parkingrest/delete/"+id,Parking.class);
    }

    @Override
    public Parking findById(Long id) {
        Parking parking = restTemplate.getForObject("http://localhost:9191/parkingrest/find/"+id,Parking.class);
        return parking;
    }

    @Override
    public Optional<Parking> editParking(Long id, String lat, String lon, String name, String website, String adress, String openingHours) {

        String send=id+'+'+lat+'+'+lon+'+'+name+'+'+website+'+'+adress+'+'+openingHours+'+';
        Parking parking = restTemplate.getForObject("http://localhost:9191/parkingrest/add/"+send,Parking.class);
        return Optional.of(parking);
    }

    @Override
    public List<Parking> findAllByNameContains(String search) {
        //dodaj search vo restot
        Parking[] parkings = restTemplate.getForObject("http://localhost:9191/parkingrest/search/"+search,Parking[].class);
        List<Parking> parkingList = Arrays.asList(parkings);
        return parkingList;
    }

    @Override
    public List<Parking> sortAllByName() {
        String sort="name";
        Parking[] parkings = restTemplate.getForObject("http://localhost:9191/parkingrest/list-all/"+sort,Parking[].class);
        List<Parking> parkingList = Arrays.asList(parkings);
        return parkingList;
    }

    @Override
    public List<Parking> sortAllByRating() {
        String sort="rating";
        Parking[] parkings = restTemplate.getForObject("http://localhost:9191/parkingrest/list-all/"+sort,Parking[].class);
        List<Parking> parkingList = Arrays.asList(parkings);
        return parkingList;
    }
}
