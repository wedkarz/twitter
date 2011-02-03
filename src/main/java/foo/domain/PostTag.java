package foo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(query = "SELECT pt FROM PostTag pt WHERE pt.post = :post ORDER BY pt.id DESC", name="postTagObj.all"),
	@NamedQuery(query = "SELECT pt.tag FROM PostTag pt WHERE pt.post = :post", name="postTag.all"),
	@NamedQuery(query = "SELECT pt FROM PostTag pt WHERE pt.tag = :tag ORDER BY pt.id DESC", name = "tagPosts.all"),
	@NamedQuery(query = "SELECT DISTINCT pt.tag FROM PostTag pt ", name = "tag.all")})
public class PostTag implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String tag;
	private Post post;

	
	@ManyToOne
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}	
	public PostTag(Post post, String tag) {
		super();
		this.post = post;
		this.tag = tag;
	}	
	public PostTag() {
		super();
	}
	
	@Id
	@GeneratedValue
	@Column(name = "post_tag_id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getTag() {
		return tag;
	}
}
