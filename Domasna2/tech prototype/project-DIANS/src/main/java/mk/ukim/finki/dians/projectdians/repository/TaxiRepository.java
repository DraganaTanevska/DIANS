package mk.ukim.finki.dians.projectdians.repository;
import mk.ukim.finki.dians.projectdians.model.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface TaxiRepository extends JpaRepository<Taxi,Long> {
}
