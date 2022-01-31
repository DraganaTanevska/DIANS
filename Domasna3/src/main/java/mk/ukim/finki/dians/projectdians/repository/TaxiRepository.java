package mk.ukim.finki.dians.projectdians.repository;

import mk.ukim.finki.dians.projectdians.model.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxiRepository extends JpaRepository<Taxi, Long> {
    List<Taxi> findAllByNameContains(String name);

    List<Taxi> findAllByFinalRatingAfter(double Rating);

    List<Taxi> findAllByOrderByFinalRatingAsc();

    List<Taxi> findAllByOrderByNameAsc();
    //  List<Taxi> findAllByPlaceType(PlaceType placeType);
}
