package foo.business;

import java.util.List;

import foo.domain.Post;
import foo.domain.PostTag;

public interface PostTagManager {

	public List<PostTag> getAllPostTags(Post p);
	public List<PostTag> getAllTagPosts(String tag);
	public List<String> getAllTags();
	
}
