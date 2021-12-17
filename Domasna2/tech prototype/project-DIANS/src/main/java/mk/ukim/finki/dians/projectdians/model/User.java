package mk.ukim.finki.dians.projectdians.model;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class User {
    @Id
    private String username;
    private String Name;
    private String Surname;
    private String email;
    private String password;


    public User() {
    }
    public User(String username,String password)
    {
this.username=username;
this.password=password;
    }

    public User(String username, String name, String surname, String email, String password) {
        this.username = username;
        Name = name;
        Surname = surname;
        this.email = email;
        this.password = password;
    }
}
