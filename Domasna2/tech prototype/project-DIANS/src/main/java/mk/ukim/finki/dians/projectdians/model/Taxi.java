package mk.ukim.finki.dians.projectdians.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Taxi extends PlaceType {

    private String phoneNumber;
    public Taxi(){}


    public Taxi(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
