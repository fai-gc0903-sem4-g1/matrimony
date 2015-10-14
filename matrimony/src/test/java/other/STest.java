/**
 * 
 */
package other;

import java.sql.Timestamp;

import com.matrimony.database.UserDAO;
import com.matrimony.entity.User;

/**
 * @author SON
 *
 */
public class STest {
	public static void main(String[] args) {
		User user=UserDAO.findById("990258dd5022352a015022352f940000");
		System.out.println(user.getExpiries());
		user.setExpiries(new Timestamp(System.currentTimeMillis()));
		UserDAO.Update(user);
		user=UserDAO.findById("990258dd5022352a015022352f940000");
		System.out.println(user.getExpiries());
	}
}
