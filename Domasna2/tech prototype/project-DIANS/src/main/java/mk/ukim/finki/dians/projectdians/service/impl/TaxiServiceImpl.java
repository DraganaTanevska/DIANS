package mk.ukim.finki.dians.projectdians.service.impl;

import mk.ukim.finki.dians.projectdians.model.Parking;
import mk.ukim.finki.dians.projectdians.model.Place;
import mk.ukim.finki.dians.projectdians.model.PlaceType;
import mk.ukim.finki.dians.projectdians.model.Taxi;
import mk.ukim.finki.dians.projectdians.repository.PlaceTypeRepository;
import mk.ukim.finki.dians.projectdians.repository.TaxiRepository;
import mk.ukim.finki.dians.projectdians.service.TaxiService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaxiServiceImpl implements TaxiService {

    private final TaxiRepository taxiRepository;

    private final PlaceTypeRepository placeTypeRepository;
    public TaxiServiceImpl(TaxiRepository taxiRepository,PlaceTypeRepository placeTypeRepository) {
        this.taxiRepository = taxiRepository;
        this.placeTypeRepository=placeTypeRepository;
    }

    @Override
    public List<Taxi> findAll() {
        return taxiRepository.findAll();
    }

    @Override
    public Optional<Taxi> saveTaxi(String name, String phoneNumber) {
        Taxi taxi=new Taxi(name,phoneNumber);
        return Optional.of(taxiRepository.save(taxi));
    }

    @Override
    public void deleteTaxi(Long id)
    {
         taxiRepository.deleteById(id);
    }

    @Override
    public Taxi findById(Long id) {
        Taxi taxi=taxiRepository.findById(id).orElse(null);
        PlaceType placeType=placeTypeRepository.findById(id).orElse(null);
        Taxi finalTaxi=new Taxi(id,placeType.getName(),placeType.getNumberOfPeopleRating(),placeType.getFinalRating(),taxi.getPhoneNumber());
        return finalTaxi;
    }

    @Override
    public Optional<Taxi> editTaxi(Long id, String name, String phoneNumber) {
        Taxi taxi=taxiRepository.findById(id).orElse(null);
        taxi.setName(name);
        taxi.setPhoneNumber(phoneNumber);
        return Optional.of(taxiRepository.save(taxi));
    }
}
