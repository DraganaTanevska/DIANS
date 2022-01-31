package mk.ukim.finki.dians.parking.repository;
import mk.ukim.finki.dians.parking.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingRepository extends JpaRepository<Parking, Long> {
    List<Parking> findAllByNameContains(String name);

    List<Parking> findAllByFinalRatingAfter(double Rating);

    List<Parking> findAllByOrderByFinalRatingAsc();

    List<Parking> findAllByOrderByNameAsc();
}
