package foo.web;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import foo.business.PostManager;
import foo.business.PostTagManager;
import foo.domain.Post;
import foo.domain.PostTag;

@ApplicationScoped
@Named
public class MainSiteBean {

	@Inject
	PostManager pm;
	
	@Inject
	PostTagManager ptm;
	
	PostTag pt = new PostTag();
	
	
	
	public List<Post> getAllPosts() {
		return pm.allPosts();
	}
	
	public List<PostTag> getAllPostTags(Post p) {
		return ptm.getAllPostTags(p);
	}
	
	public List<String> getAllTags() {
		return ptm.getAllTags();
	}
	
	public List<PostTag> getAllTagPosts() {
		return ptm.getAllTagPosts(pt.getTag());
	}
	
	
	// Actions
	public String showTagPosts(String t) {
		this.pt.setId(0);
		this.pt.setTag(t);
		return "showTagPosts";
	}
	
}
