package mk.ukim.finki.dians.service.impl;

import mk.ukim.finki.dians.repository.PlaceTypeRepository;
import mk.ukim.finki.dians.service.PlaceTypeService;
import org.springframework.stereotype.Service;

@Service
public class PlaceTypeServiceImpl implements PlaceTypeService {
    private final PlaceTypeRepository placeTypeRepository;

    public PlaceTypeServiceImpl(PlaceTypeRepository placeTypeRepository) {
        this.placeTypeRepository = placeTypeRepository;
    }
}

