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

    @NotBlank
    @Column(nullable = false)
    private String uploadedDate;

    @NotBlank
    @Column(nullable = false)
    //@JsonIgnore
    String location;

    private int likes = 0;
    private int dislikes = 0;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn
    private User pictureOwner;

    //@OneToOne
    //private User avatarOwner;

    public Picture() {
    }

    public Picture(@NotBlank String pictureName,
                   @NotBlank String uploadedDate,
                   @NotBlank String location,
                   int likes,
                   int dislikes) {
        this.name = pictureName;
        this.uploadedDate = uploadedDate;
        this.location = location;
        this.likes = likes;
        this.dislikes = dislikes;
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public void addLike() {
        this.likes++;
    }

    public void addDisLike() {
        this.dislikes++;
    }

    /*public User getAvatarOwner() {
        return avatarOwner;
    }

    public void setAvatarOwner(User avatarOwner) {
        this.avatarOwner = avatarOwner;
    }*/
}
