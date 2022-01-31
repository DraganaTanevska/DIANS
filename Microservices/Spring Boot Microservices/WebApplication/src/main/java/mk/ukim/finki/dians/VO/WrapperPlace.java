package mk.ukim.finki.dians.VO;

import mk.ukim.finki.dians.model.Place;

public class WrapperPlace {
    Place place;
    public WrapperPlace(Place place){
        this.place=place;
    }

    public Place getPlace() {
        return place;
    }
}
