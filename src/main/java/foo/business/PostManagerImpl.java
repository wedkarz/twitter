package foo.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import foo.domain.Post;
import foo.domain.PostTag;
import foo.domain.User;

@Stateless
public class PostManagerImpl implements PostManager {

	@PersistenceContext
	EntityManager em;

	private List<PostTag> generateTags(Post p, String body) {
		List<PostTag> tags = new ArrayList<PostTag>();
		java.util.regex.Pattern pattern = java.util.regex.Pattern
				.compile("(#[a-zA-Z_0-9]+)");
		Matcher matcher = pattern.matcher((CharSequence) body);

		while (matcher.find()) {
			tags.add(new PostTag(p, matcher.group().substring(1)));
		}

		return tags;
	}

	private void cleanTags(Post p) {
		List<PostTag> ptags = em.createNamedQuery("postTagObj.all")
				.setParameter("post", p).getResultList();
		
		for (PostTag pt : ptags)
			em.remove(pt);
	}

	public void addPost(String body, User user) {
		Post p = new Post();

		p.setBody(body);
		p.setCreated_at(new Date()); // assigns current date
		p.setUser(user);
		p.setTags(this.generateTags(p, body));

		em.persist(p);
	}

	public void removePost(Post p) {
		em.remove(em.find(Post.class, p.getId()));
	}

	public Post readPost(long id) {
		return em.find(Post.class, id);
	}

	public void updatePost(Post p) {
		Post post = (Post) em.find(Post.class, p.getId());
		Collection<PostTag> tagss = p.getTags();
		
		if (p.getBody().compareTo(post.getBody()) != 0) {
			this.cleanTags(post);
			tagss = this.generateTags(post, p.getBody());
		}
		post.setBody(p.getBody());
		post.setTags(tagss);

		em.merge(post);

	}

	public List<Post> allPosts() {
		return em.createNamedQuery("post.all").getResultList();
	}

}
