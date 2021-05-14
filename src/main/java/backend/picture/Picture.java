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

    /*@NotBlank
    @Column(nullable = false)
    private String likes;*/

    @NotBlank
    @Column(nullable = false)
    private String uploadedDate;

    @NotBlank
    @Column(nullable = false)
    String location;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private User pictureOwner;

    public Picture() {
    }

    public Picture(String pictureName, String uploadedDate, String location) {
        this.name = pictureName;
        this.location = location;
        this.uploadedDate = uploadedDate;
    }

    /*public Picture(String pictureName, String likes, String uploadedDate, String location) {
        this.name = pictureName;
        this.likes = likes;
        this.location = location;
        this.uploadedDate = uploadedDate;
    }*/

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

/*    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }*/

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(String uploadedDate) {
        this.uploadedDate = uploadedDate;
    }
    public User getPictureOwner() {
        return pictureOwner;
    }

    public void setPictureOwner(User pictureOwner) {
        this.pictureOwner = pictureOwner;
    }
}
