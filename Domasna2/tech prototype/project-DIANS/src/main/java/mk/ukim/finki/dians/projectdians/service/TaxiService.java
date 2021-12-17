package mk.ukim.finki.dians.projectdians.service;

import mk.ukim.finki.dians.projectdians.model.Taxi;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface TaxiService {
    List<Taxi> findAll();
    Optional<Taxi> saveTaxi(String name, String phoneNumber);
    void deleteTaxi(Long id);
    Optional<Taxi> findById(Long id);
}
