//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package mk.ukim.finki.dians.service.impl;

import mk.ukim.finki.dians.model.Rating;
import mk.ukim.finki.dians.repository.PlaceTypeRepository;
import mk.ukim.finki.dians.repository.RatingRepository;
import mk.ukim.finki.dians.repository.UserRepository;
import mk.ukim.finki.dians.service.RatingService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {
    UserRepository userRepository;
    PlaceTypeRepository placeTypeRepository;
    RatingRepository ratingRepository;
    private final RestTemplate restTemplate;
    public RatingServiceImpl(RatingRepository ratingRepository, UserRepository userRepository, PlaceTypeRepository placeTypeRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.placeTypeRepository = placeTypeRepository;
        this.ratingRepository = ratingRepository;

        this.restTemplate = restTemplate;
    }

    public Optional<Rating> save(Long id, String username, Integer rating) {

        String send=id.toString()+'+'+username+ rating.toString();
        Rating rating1= restTemplate.getForObject("http://localhost:9191/ratingrest/save/"+send,Rating.class);

        return Optional.of(rating1);


    }
}
