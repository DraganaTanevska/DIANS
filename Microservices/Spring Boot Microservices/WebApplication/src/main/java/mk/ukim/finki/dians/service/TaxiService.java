package mk.ukim.finki.dians.service;

import mk.ukim.finki.dians.model.Taxi;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TaxiService {
    List<Taxi> findAll();

    Taxi saveTaxi(String name, String phoneNumber);

    void deleteTaxi(Long id);

    Taxi findById(Long id);

    Optional<Taxi> editTaxi(Long id, String name, String phoneNumber);

    /**
     * this function should implement the search function
     *
     * @return List<Places>
     */
    List<Taxi> findAllByNameContains(String name);

    /**
     * this function should implement the sort function by Name in ascending order
     *
     * @return List<Places>
     */
    List<Taxi> sortAllByName();

    /**
     * this function should implement the sort function by Rating in ascending order
     *
     * @return List<Taxi>
     */
    List<Taxi> sortAllByRating();

}
