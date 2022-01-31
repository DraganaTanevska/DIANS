package mk.ukim.finki.dians.taxi.model.exceptions;

public class TaxiIdNotFoundException extends RuntimeException {
    public TaxiIdNotFoundException(Long id) {
        super(String.format("Taxi with id: %s does not exist", id));
    }
}
