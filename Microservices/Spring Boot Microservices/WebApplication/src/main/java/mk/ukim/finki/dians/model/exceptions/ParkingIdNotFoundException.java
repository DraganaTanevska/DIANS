package mk.ukim.finki.dians.model.exceptions;

public class ParkingIdNotFoundException extends RuntimeException {
    public ParkingIdNotFoundException(Long id) {
        super(String.format("Taxi with id: %s does not exist", id));
    }
}
