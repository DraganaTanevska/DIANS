package mk.ukim.finki.dians.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Taxi extends PlaceType {

    private String phoneNumber;

    public Taxi() {
    }


    public Taxi(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Taxi(Long id, String name, int numberOfPeopleRating, double finalRating, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.Id = id;
        this.finalRating = finalRating;
        this.numberOfPeopleRating = numberOfPeopleRating;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
