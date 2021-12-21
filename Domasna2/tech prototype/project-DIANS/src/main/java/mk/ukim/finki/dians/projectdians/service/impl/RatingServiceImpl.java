//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package mk.ukim.finki.dians.projectdians.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import mk.ukim.finki.dians.projectdians.model.PlaceType;
import mk.ukim.finki.dians.projectdians.model.Rating;
import mk.ukim.finki.dians.projectdians.model.User;
import mk.ukim.finki.dians.projectdians.repository.PlaceTypeRepository;
import mk.ukim.finki.dians.projectdians.repository.RatingRepository;
import mk.ukim.finki.dians.projectdians.repository.UserRepository;
import mk.ukim.finki.dians.projectdians.service.RatingService;
import org.springframework.stereotype.Service;

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

    public Optional<Rating> save(Long id, String username, int rating) {
        User user = this.userRepository.findByUsername(username);
        PlaceType placeType = this.placeTypeRepository.findById(id).orElse(null);
        double placeTypeRating = placeType.getFinalRating();
        int placeTypeNumberOfPeopleRating = placeType.getNumberOfPeopleRating();
        placeTypeRating = (placeTypeRating * (double)placeTypeNumberOfPeopleRating + (double)rating) / (double)(placeTypeNumberOfPeopleRating + 1);
        ++placeTypeNumberOfPeopleRating;
        placeType.setFinalRating(placeTypeRating);
        placeType.setNumberOfPeopleRating(placeTypeNumberOfPeopleRating);
        LocalDateTime dateTime = LocalDateTime.now();
        Rating ratingObject = new Rating(placeType, user, rating, dateTime);
        return Optional.of((Rating)this.ratingRepository.save(ratingObject));
    }
}
