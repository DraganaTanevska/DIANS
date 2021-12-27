package mk.ukim.finki.dians.projectdians.repository;
import mk.ukim.finki.dians.projectdians.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place,Long> {
    List<Place> findAllByNameContains(String name);
    List<Place> findAllByFinalRatingAfter(double Rating);
    List<Place> findAllByOrderByFinalRatingAsc();
    List<Place> findAllByOrderByNameAsc();
}
