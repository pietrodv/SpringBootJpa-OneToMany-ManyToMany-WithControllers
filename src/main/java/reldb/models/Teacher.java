package reldb.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "teacher_id")
    private Integer id;

    @NotEmpty(message = "Please provide a name")
    private String name;

    private String instrument;

    @ManyToMany(mappedBy = "teachers")
    private List<User> users;

    @OneToMany(mappedBy = "teacher")
    private List<UserTeacher> usersTeachers;



    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserTeacher> getUsersTeachers() {
        return usersTeachers;
    }

    public void setUsersTeachers(List<UserTeacher> usersTeachers) {
        this.usersTeachers = usersTeachers;
    }

}
