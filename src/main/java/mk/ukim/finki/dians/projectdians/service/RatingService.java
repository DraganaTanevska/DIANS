//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package mk.ukim.finki.dians.projectdians.service;

import mk.ukim.finki.dians.projectdians.model.Rating;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface RatingService {

    Optional<Rating> save(Long id, String username, int rating);
}
