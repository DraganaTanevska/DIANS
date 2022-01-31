package mk.ukim.finki.dians.projectdians.service.impl;

import mk.ukim.finki.dians.projectdians.repository.PlaceTypeRepository;
import mk.ukim.finki.dians.projectdians.service.PlaceTypeService;
import org.springframework.stereotype.Service;

@Service
public class PlaceTypeServiceImpl implements PlaceTypeService {
    private final PlaceTypeRepository placeTypeRepository;

    public PlaceTypeServiceImpl(PlaceTypeRepository placeTypeRepository) {
        this.placeTypeRepository = placeTypeRepository;
    }
}

