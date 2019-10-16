package reldb.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name= "photo_id")
    private Integer id;

    private String photoUUID;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore // Add this to avoid infinite loop!
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhotoUUID() {
        return photoUUID;
    }

    public void setPhotoUUID(String photoUUID) {
        this.photoUUID = photoUUID;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Photo() {}

    public Photo (String photoUUID, User user) {
        this.photoUUID = photoUUID;
        this.user = user;
    }

}
