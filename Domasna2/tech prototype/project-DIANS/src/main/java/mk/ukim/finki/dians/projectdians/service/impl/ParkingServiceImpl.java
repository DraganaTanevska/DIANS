package mk.ukim.finki.dians.projectdians.service.impl;

import mk.ukim.finki.dians.projectdians.model.Parking;
import mk.ukim.finki.dians.projectdians.repository.ParkingRepository;
import mk.ukim.finki.dians.projectdians.service.ParkingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingServiceImpl implements ParkingService {
    private final ParkingRepository parkingRepository;
    public ParkingServiceImpl(ParkingRepository parkingRepository){

        this.parkingRepository=parkingRepository;
    }

    @Override
    public List<Parking> findALlParking() {
        return parkingRepository.findAll();
    }

    @Override
    public Optional<Parking> saveParking(String lat, String lon, String name, String website, String adress, String openingHours) {
        Parking parking=new Parking(lat,lon,name,website,adress,openingHours);
        return Optional.of(parkingRepository.save(parking));
    }

    @Override
    public void deleteParking(Long id) {
        parkingRepository.delete(parkingRepository.getById(id));
    }

    @Override
    public Optional<Parking> findById(Long id) {
        return Optional.of(parkingRepository.getById(id));
    }
}
