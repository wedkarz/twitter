package foo.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({ @NamedQuery(name="user.all", query="from User"),
@NamedQuery(name="userPass.one", query="SELECT u FROM User u WHERE u.nickname = :nickname OR u.email = :email"),
@NamedQuery(name="userByEmail.all", query="SELECT u FROM User u WHERE u.email = :email"),
@NamedQuery(name="userByNickname.all", query="SELECT u FROM User u WHERE u.nickname = :nickname")
})
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	public enum Gender {
		MALE, FEMALE
	}
	private long id;
	private String email;
	private String nickname;
	
	private String password;
	
	private String name;
	private String surname;
	private Date dob;
	private Gender gender;
	
	private Collection<Post> posts;
	
	public User() {
		dob = new Date();
	}
	
	// setters and getters
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	@Enumerated(EnumType.STRING)
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	@OneToMany(cascade = CascadeType.ALL,
			mappedBy = "user")
	public Collection<Post> getPosts() {
		return posts;
	}
	public void setPosts(Collection<Post> posts) {
		this.posts = posts;
	}

	
}
