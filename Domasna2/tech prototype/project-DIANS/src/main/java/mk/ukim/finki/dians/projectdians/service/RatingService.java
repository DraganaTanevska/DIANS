//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package mk.ukim.finki.dians.projectdians.service;

import java.util.Optional;
import mk.ukim.finki.dians.projectdians.model.Rating;
import org.springframework.stereotype.Service;

@Service
public interface RatingService {
    Optional<Rating> save(Long id, String username, int rating);
}
