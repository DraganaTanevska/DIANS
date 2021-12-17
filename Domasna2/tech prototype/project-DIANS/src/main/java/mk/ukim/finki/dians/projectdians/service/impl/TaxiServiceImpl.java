package mk.ukim.finki.dians.projectdians.service.impl;

import mk.ukim.finki.dians.projectdians.model.Taxi;
import mk.ukim.finki.dians.projectdians.repository.TaxiRepository;
import mk.ukim.finki.dians.projectdians.service.TaxiService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaxiServiceImpl implements TaxiService {

    private final TaxiRepository taxiRepository;

    public TaxiServiceImpl(TaxiRepository taxiRepository) {
        this.taxiRepository = taxiRepository;
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
         taxiRepository.delete(taxiRepository.getById(id));
    }

    @Override
    public Optional<Taxi> findById(Long id) {
        return Optional.of(taxiRepository.getById(id));
    }
}
