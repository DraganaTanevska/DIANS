package mk.ukim.finki.dians.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Place extends PlaceType {


    private String lat;
    private String lon;
    private String website;
    private String addr_street;
    private String opening_hours;

    public Place() {
    }

    public Place(String lat, String lon, String name, String website, String addr_street, String opening_hours) {

        this.lat = lat;
        this.lon = lon;
        this.name = name;
        this.website = website;
        this.addr_street = addr_street;
        this.opening_hours = opening_hours;
    }

    public Place(Long id, String name, int numberOfPeopleRating, double finalRating, String lat, String lon, String website, String addr_street, String opening_hours) {
        this.Id = id;
        this.finalRating = finalRating;
        this.numberOfPeopleRating = numberOfPeopleRating;
        this.lat = lat;
        this.lon = lon;
        this.name = name;
        this.website = website;
        this.addr_street = addr_street;
        this.opening_hours = opening_hours;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }


    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddr_street() {
        return addr_street;
    }

    public void setAddr_street(String addr_street) {
        this.addr_street = addr_street;
    }

    public String getOpening_hours() {
        return opening_hours;
    }

    public void setOpening_hours(String opening_hours) {
        this.opening_hours = opening_hours;
    }
}
