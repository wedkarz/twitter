package foo.business;

import java.util.List;

import foo.domain.Post;
import foo.domain.User;

public interface PostManager {
	public void addPost(String body, User user);
	public void removePost(Post p);
	public void updatePost(Post p);
	public Post readPost(long id);
	
	public List<Post> allPosts();
}
