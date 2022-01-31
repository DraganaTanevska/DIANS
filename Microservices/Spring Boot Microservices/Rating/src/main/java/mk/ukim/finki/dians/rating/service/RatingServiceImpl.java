//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package mk.ukim.finki.dians.rating.service;

import mk.ukim.finki.dians.rating.model.PlaceType;
import mk.ukim.finki.dians.rating.model.Rating;
import mk.ukim.finki.dians.rating.model.User;
import mk.ukim.finki.dians.rating.repository.PlaceTypeRepository;
import mk.ukim.finki.dians.rating.repository.RatingRepository;
import mk.ukim.finki.dians.rating.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {
    UserRepository userRepository;
    PlaceTypeRepository placeTypeRepository;
    RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository, UserRepository userRepository, PlaceTypeRepository placeTypeRepository) {
        this.userRepository = userRepository;
        this.placeTypeRepository = placeTypeRepository;
        this.ratingRepository = ratingRepository;

    }

    public Optional<Rating> save(Long id, String username, Integer rating) {
        User user = this.userRepository.findByUsername(username).orElse(null);
        PlaceType placeType = this.placeTypeRepository.findById(id).orElse(null);
        double placeTypeRating = placeType.getFinalRating();
        int placeTypeNumberOfPeopleRating = placeType.getNumberOfPeopleRating();
        placeTypeRating = (placeTypeRating * (double) placeTypeNumberOfPeopleRating + (double) rating) / (double) (placeTypeNumberOfPeopleRating + 1);
        ++placeTypeNumberOfPeopleRating;
        placeType.setFinalRating(placeTypeRating);
        placeType.setNumberOfPeopleRating(placeTypeNumberOfPeopleRating);
        LocalDateTime dateTime = LocalDateTime.now();
        Rating ratingObject = new Rating(placeType, user, rating, dateTime);
        return Optional.of(this.ratingRepository.save(ratingObject));
    }
}
