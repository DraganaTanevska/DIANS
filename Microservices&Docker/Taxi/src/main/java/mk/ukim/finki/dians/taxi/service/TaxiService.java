package mk.ukim.finki.dians.taxi.service;

import mk.ukim.finki.dians.taxi.model.Taxi;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaxiService {
    List<Taxi> findAll();

    Taxi saveTaxi(String name, String phoneNumber);

    Taxi deleteTaxi(Long id);

    Taxi findById(Long id);

    Taxi editTaxi(Long id, String name, String phoneNumber);

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
