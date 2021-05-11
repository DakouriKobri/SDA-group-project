package backend.picture;

import backend.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Table
@Entity
public class Picture {

    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @NotBlank
    @Column(nullable = false)
    String location;

    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    private User pictureOwner;

    public Picture(User pictureOwner) {
        this.pictureOwner = pictureOwner;
    }

    public Picture(String pictureName, String location) {
        this.name = pictureName;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
