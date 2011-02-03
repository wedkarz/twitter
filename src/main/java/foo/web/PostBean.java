package foo.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import foo.business.PostManager;
import foo.domain.Post;
import foo.domain.User;

@Named
@SessionScoped
public class PostBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	PostManager pm;

	@Inject
	UserBean ub;

	Post post = new Post();

	String body;
	User user;
	long id;


	/////////// ACTIONS
	// NEW
	public String newPost() {
		this.body = "";
		this.user = ub.getUser();
		return "newPostForm";
	}

	// SAVE_NEW
	public String savePost() {
		pm.addPost(body, ub.getUser());
		return "home";
	}

	// DELETE
	public String removePost(Post p) {
		pm.removePost(p);
		return "home";
	}

	// EDIT
	public String editPost(Post p) {
		this.post = p;
		this.user = p.getUser();
		this.id = p.getId();
		return "editPostForm";
	}

	// UPDATE
	public String updatePost() {
		pm.updatePost(this.post);
		return "home";
	}

	public boolean isAuthor(User u, Post p) {
		if (u != null) {
			if (u.getId() == p.getUser().getId())
				return true;
		}
		
		return false;
	}
	
	
	/////////////////////
	// GETTERS & SETTERS
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@NotEmpty(message = "Nie moze byc puste")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Pattern(regexp = ".{3,255}", message = "Minimalna dlugosc to 3, maksymalna 255 znakow")
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
