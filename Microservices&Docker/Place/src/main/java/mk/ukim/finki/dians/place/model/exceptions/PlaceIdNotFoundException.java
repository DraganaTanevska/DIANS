package mk.ukim.finki.dians.place.model.exceptions;

public class PlaceIdNotFoundException extends RuntimeException {
    public PlaceIdNotFoundException(Long id) {
        super(String.format("Taxi with id: %s does not exist", id));
    }
}
