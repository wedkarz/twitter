package foo.business;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import foo.domain.User;

public interface UserManager {
	public User addUser(String email, String nickname, String password);
	public void removeUser(User u);
	public void updateUser(User u);
	public List<User> findUserByEmail(String s);
	public List<User> findUserByNickname(String s);
	
	public User loginUser(String email, String nickname, String password);
}
