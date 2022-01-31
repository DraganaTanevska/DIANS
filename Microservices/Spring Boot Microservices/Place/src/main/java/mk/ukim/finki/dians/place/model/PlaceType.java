package mk.ukim.finki.dians.place.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class PlaceType {
    //
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long Id;
    protected String name;
    protected double finalRating;
    protected int numberOfPeopleRating;

    public PlaceType() {
    }

    public PlaceType(String name) {

        this.name = name;
        this.finalRating = 0;
        this.numberOfPeopleRating = 0;
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFinalRating() {
        return finalRating;
    }

    public void setFinalRating(double finalRating) {
        this.finalRating = finalRating;
    }

    public int getNumberOfPeopleRating() {
        return numberOfPeopleRating;
    }

    public void setNumberOfPeopleRating(int numberOfPeopleRating) {
        this.numberOfPeopleRating = numberOfPeopleRating;
    }
}
