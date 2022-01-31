package mk.ukim.finki.dians.projectdians.repository;

import mk.ukim.finki.dians.projectdians.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findAllByNameContains(String name);

    List<Place> findAllByOrderByFinalRatingAsc();

    List<Place> findAllByOrderByNameAsc();
}
