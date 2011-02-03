package foo.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import foo.domain.Post;
import foo.domain.PostTag;


@Stateless
public class PostTagManagerImpl implements PostTagManager {

	@PersistenceContext
	EntityManager em;
	
	public List<PostTag> getAllPostTags(Post p) {
		return em.createNamedQuery("postTag.all").setParameter("post", p).getResultList();
	}

	public List<PostTag> getAllTagPosts(String tag) {
		return em.createNamedQuery("tagPosts.all").setParameter("tag", tag).getResultList();
	}

	public List<String> getAllTags() {
		return em.createNamedQuery("tag.all").getResultList();
	}

}
