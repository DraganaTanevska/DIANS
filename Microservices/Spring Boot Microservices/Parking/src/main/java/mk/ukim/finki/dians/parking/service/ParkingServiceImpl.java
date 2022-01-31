package mk.ukim.finki.dians.parking.service;

import mk.ukim.finki.dians.parking.model.Parking;
import mk.ukim.finki.dians.parking.model.PlaceType;
import mk.ukim.finki.dians.parking.repository.ParkingRepository;
import mk.ukim.finki.dians.parking.model.exceptions.ParkingIdNotFoundException;
import mk.ukim.finki.dians.parking.repository.PlaceTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingServiceImpl implements ParkingService {
    private final ParkingRepository parkingRepository;
    private final PlaceTypeRepository placeTypeRepository;

    public ParkingServiceImpl(ParkingRepository parkingRepository, PlaceTypeRepository placeTypeRepository) {
        this.placeTypeRepository = placeTypeRepository;
        this.parkingRepository = parkingRepository;
    }

    @Override
    public List<Parking> findALlParking() {
        return parkingRepository.findAll();
    }

    @Override
    public Parking saveParking(String lat, String lon, String name, String website, String adress, String openingHours) {
        Parking parking = new Parking(lat, lon, name, website, adress, openingHours);
        return parkingRepository.save(parking);
    }

    @Override
    public Parking deleteParking(Long id) {
        Parking parking= this.findById(id);
        parkingRepository.deleteById(id);
        return parking;
    }

    @Override
    public Parking findById(Long id) {
        Parking parking1 = parkingRepository.findById(id).orElseThrow(() -> new ParkingIdNotFoundException(id));
        PlaceType placeType = placeTypeRepository.findById(id).orElse(null);
        Parking finalParking = new Parking(id, placeType.getName(), placeType.getNumberOfPeopleRating(), placeType.getFinalRating(), parking1.getLat(), parking1.getLon(), parking1.getWebsite(), parking1.getAddr_street(), parking1.getOpening_hours());
        return finalParking;
    }

    @Override
    public Parking editParking(Long id, String lat, String lon, String name, String website, String adress, String openingHours) {
        Parking parking = this.findById(id);
        parking.setAddr_street(adress);
        parking.setLon(lon);
        parking.setLat(lat);
        parking.setWebsite(website);
        parking.setOpening_hours(openingHours);
        parking.setName(name);
        return parkingRepository.save(parking);
    }

    @Override
    public List<Parking> findAllByNameContains(String name) {
        return parkingRepository.findAllByNameContains(name);
    }

    @Override
    public List<Parking> sortAllByName() {

        return parkingRepository.findAllByOrderByNameAsc();
    }

    @Override
    public List<Parking> sortAllByRating() {
        return parkingRepository.findAllByOrderByFinalRatingAsc();
    }
}
