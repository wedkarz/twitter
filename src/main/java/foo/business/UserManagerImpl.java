package foo.business;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import foo.domain.User;

@Stateless
public class UserManagerImpl implements UserManager {

	@PersistenceContext
	EntityManager em;

	public static String bytesToHex(byte[] b) {
	      char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7',
	                         '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
	      StringBuffer buf = new StringBuffer();
	      for (int j=0; j<b.length; j++) {
	         buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
	         buf.append(hexDigit[b[j] & 0x0f]);
	      }
	      return buf.toString();
	   }
	
	public User addUser(String email, String nickname, String password) {
		User u = new User();
		u.setEmail(email);
		u.setNickname(nickname);
		u.setPassword(password);

		em.persist(u);
		return u;
	}

	public void removeUser(User u) {
		em.remove(em.find(User.class, u.getId()));
	}

	public void updateUser(User u) {
		em.merge(u);
	}

	public List<User> findUserByEmail(String s) {
		return em.createNamedQuery("userByEmail.all").setParameter("email", s).getResultList();
	}
	
	public List<User> findUserByNickname(String s) {
		return em.createNamedQuery("userByNickname.all").setParameter("nickname", s).getResultList();
	}

	public User loginUser(String email, String nickname, String password) {
		List<User> u = null;

		u = em.createNamedQuery("userPass.one").setParameter("email", email)
				.setParameter("nickname", nickname).getResultList();
		
		System.out.println("POBRANO " + u.size() + " userow");

		if (u.size() == 1) {
			if (u.get(0).getPassword().compareTo(password) != 0) {
				System.out.println("Haslo uzytkownika" + u.get(0).getPassword() + " nie pasuje do" + password);
				u = null;
			} else return u.get(0);
		}
		
		return null;
	}


}
