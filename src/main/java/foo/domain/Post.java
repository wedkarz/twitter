package foo.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQuery(query = "FROM Post p ORDER BY p.id DESC", name="post.all")
public class Post implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private User user;
	private Date created_at;
	private String body;
	private Collection<PostTag> tags;
	
	@Id
	@GeneratedValue
	@Column(name = "post_id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@OneToMany(cascade = CascadeType.ALL,
			mappedBy = "post")
	public Collection<PostTag> getTags() {
		return tags;
	}
	public void setTags(Collection<PostTag> tags) {
		this.tags = tags;
	}
	@ManyToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
