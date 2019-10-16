package reldb.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users_teachers")
public class UserTeacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "ut_id")
    private Integer id;

    LocalDateTime registeredAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    User user;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonIgnore
    Teacher teacher;




    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserTeacher(){}

    public UserTeacher(User user, Teacher teacher) {
        this.user = user;
        this.teacher = teacher;
    }
}
