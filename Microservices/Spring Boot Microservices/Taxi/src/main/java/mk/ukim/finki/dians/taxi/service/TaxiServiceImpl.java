package mk.ukim.finki.dians.taxi.service;

import mk.ukim.finki.dians.taxi.model.PlaceType;
import mk.ukim.finki.dians.taxi.model.Taxi;
import mk.ukim.finki.dians.taxi.model.exceptions.TaxiIdNotFoundException;
import mk.ukim.finki.dians.taxi.repository.PlaceTypeRepository;
import mk.ukim.finki.dians.taxi.repository.TaxiRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxiServiceImpl implements TaxiService {

    private final TaxiRepository taxiRepository;

    private final PlaceTypeRepository placeTypeRepository;

    public TaxiServiceImpl(TaxiRepository taxiRepository, PlaceTypeRepository placeTypeRepository) {
        this.taxiRepository = taxiRepository;
        this.placeTypeRepository = placeTypeRepository;
    }

    @Override
    public List<Taxi> findAll() {
        return taxiRepository.findAll();
    }

    @Override
    public Taxi saveTaxi(String name, String phoneNumber) {
        Taxi taxi = new Taxi(name, phoneNumber);
        return taxiRepository.save(taxi);
    }

    @Override
    public Taxi deleteTaxi(Long id) {
        Taxi taxi=this.findById(id);
        taxiRepository.deleteById(id);
        return taxi;
    }

    @Override
    public Taxi findById(Long id) {
        Taxi taxi = taxiRepository.findById(id).orElseThrow(() -> new TaxiIdNotFoundException(id));
        PlaceType placeType = placeTypeRepository.findById(id).orElse(null);
        Taxi finalTaxi = new Taxi(id, placeType.getName(), placeType.getNumberOfPeopleRating(), placeType.getFinalRating(), taxi.getPhoneNumber());
        return finalTaxi;
    }

    @Override
    public Taxi editTaxi(Long id, String name, String phoneNumber) {
        Taxi taxi = this.findById(id);
        taxi.setName(name);
        taxi.setPhoneNumber(phoneNumber);
        return taxiRepository.save(taxi);
    }

    @Override
    public List<Taxi> findAllByNameContains(String name) {
        return taxiRepository.findAllByNameContains(name);
    }

    @Override
    public List<Taxi> sortAllByName() {

        return taxiRepository.findAllByOrderByNameAsc();
    }

    @Override
    public List<Taxi> sortAllByRating() {
        return taxiRepository.findAllByOrderByFinalRatingAsc();
    }
}
