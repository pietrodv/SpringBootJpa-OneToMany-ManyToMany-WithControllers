package reldb.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name= "user_id")
    private Integer id;

    @NotEmpty(message = "Please provide a name")
    private String name;

    @OneToMany(mappedBy = "user")
    private List<Photo> photos;

    @ManyToMany
	@JoinTable(name = "users_teachers",
			joinColumns = { @JoinColumn(name = "user_id") },
			inverseJoinColumns = { @JoinColumn(name = "teacher_id") })
	@JsonIgnore
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "user")
	private List<UserTeacher> usersTeachers;




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

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public List<UserTeacher> getUsersTeachers() {
		return usersTeachers;
	}

	public void setUsersTeachers(List<UserTeacher> usersTeachers) {
		this.usersTeachers = usersTeachers;
	}

	public User(){}

	public User(String name) {
		this.name = name;
	}
}


