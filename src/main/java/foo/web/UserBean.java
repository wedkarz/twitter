package foo.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

import foo.business.UserManager;
import foo.domain.Post;
import foo.domain.User;
import foo.domain.User.Gender;

@SessionScoped
@Named
public class UserBean implements Serializable {

	@Inject
	UserManager um;

	private static final long serialVersionUID = 1L;
	private String email;
	private String nickname;
	private String password;

	private String name;
	private String surname;
	private Date dob;
	private Gender gender;

	private String login;

	private List<SelectItem> genders;
	private User user;
	private Collection<Post> posts;

	public UserBean() {
		genders = new ArrayList<SelectItem>();
		genders.add(new SelectItem(User.Gender.MALE, "male", "male"));
		genders.add(new SelectItem(User.Gender.FEMALE, "female", "female"));
	}

	// ///////////
	// ACTIONS
	public String editUser() {
		email = user.getEmail();
		nickname = user.getNickname();
		password = user.getPassword();
		name = user.getName();
		surname = user.getSurname();
		dob = user.getDob();
		gender = user.getGender();

		return "userEditForm";
	}

	public String updateUser() {
		user.setEmail(email);
		user.setNickname(nickname);
		user.setPassword(password);
		user.setName(name);
		user.setSurname(surname);
		user.setDob(dob);
		user.setGender(gender);

		um.updateUser(user);
		return "editUserForm";
	}

	public String register() {
		this.email = null;
		this.nickname = null;
		this.password = null;
		return "userRegisterForm";
	}

	public String addUser() {
		user = um.addUser(this.email, this.nickname, this.password);

		this.email = null;
		this.nickname = null;
		this.password = null;
		return "home";
	}

	public String loginUser() {
		user = um.loginUser(this.login, this.login, this.password);

		if (user != null) {
			this.login = null;
			this.password = null;
			return "home";
		} else {
			this.login = null;
			this.password = null;
			FacesContext.getCurrentInstance().addMessage("ERROR",
					new FacesMessage("Incorrect login data"));

			return "userLoginForm";
		}
	}

	@Email
	public String getEmail() {
		return email;
	}

	public String logout() {
		user = null;
		return "home";
	}
	
	// VALIDATORS
	public void validateEmail(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {

		List<User> usrList = um.findUserByEmail((String) arg2);

		if (usrList != null) {
			if (usrList.size() > 0)
				throw new ValidatorException(new FacesMessage("*",
						"Email already exists in our database"));
		}
	}

	public void validateNickname(FacesContext arg0, UIComponent arg1,
			Object arg2) throws ValidatorException {

		List<User> usrList = um.findUserByNickname((String) arg2);

		if (usrList != null) {
			if (usrList.size() > 0)
				throw new ValidatorException(new FacesMessage("*",
						"Nickname already exists in our database"));
		}
	}

	// Getters & Setters
	public void setEmail(String email) {
		this.email = email;
	}

	@Pattern(regexp = "\\w+", message = "can not be empty")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Pattern(regexp = ".{4,}", message = "should have minimum 4 characters")
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Collection<Post> getPosts() {
		return posts;
	}

	public void setPosts(Collection<Post> posts) {
		this.posts = posts;
	}

	public User getUser() {
		return user;
	}

	public List<SelectItem> getGenders() {
		return genders;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String removeUser() {
		um.removeUser(user);
		user = null;

		return "home";
	}

}
