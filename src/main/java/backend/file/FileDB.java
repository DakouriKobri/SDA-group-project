/*
package backend.file;

import backend.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table
public class FileDB {


    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Lob
    private byte[] data;

    @NotBlank
    @Column(nullable = false)
    private String likes;


    @NotBlank
    @Column(nullable = false)
    private String location;

    @JsonIgnore
    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    private User fileOwner;

    TO USER___________________
    @OneToOne(mappedBy = "fileOwner",targetEntity= FileDB.class)
    private FileDB fileDB;
    ___________________________



    //constructors

    public FileDB() {
    }


 */
/*   public FileDB(@NotBlank String name, @NotBlank String type, String like) {
        this.name = name;
        this.type = type;
        this.likes = like;
    }*//*



    //getters and setters

    public User getFileOwner() {
        return fileOwner;
    }

    public void setFileOwner(User fileOwner) {
        this.fileOwner = fileOwner;
    }

    public Long getId() {
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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
*/
