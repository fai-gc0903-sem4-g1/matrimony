/**
 * 
 */
package com.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.time.DateUtils;

import com.matrimony.database.UserDAO;
import com.matrimony.entity.User;
import com.matrimony.exception.STException.ContactNumberAlready;
import com.matrimony.exception.STException.EmailAlready;

/**
 * @author SON
 *
 */
public class UserMaker {
	private Random random = new Random();
	private final String[] phoneHeader = { "096", "0167", "098" };
	private final String[] gender = { "male", "female" };
	private final String[] emailTypes = { "@yahoo.com", "@yahoo.com.vn", "@gmail.com", "@gmail.com.vn" };
	// private final String[] maritalStatus = { "widowed", "separated",
	// "maried", "single", "divorced" };
	private List<String> nameList, emailList, cityList;

	/**
	 * 
	 */
	public UserMaker() {
		FileInputStream fileName = null, fileEmail = null, fileCity = null;
		try {
			fileName = new FileInputStream(System.getProperty("user.home") + "/Desktop/name.txt");
			fileEmail = new FileInputStream(System.getProperty("user.home") + "/Desktop/email.txt");
			fileCity = new FileInputStream(System.getProperty("user.home") + "/Desktop/city.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nameList = makeListLineByLine(fileName);
		emailList = makeListLineByLine(fileEmail);
		cityList = makeListLineByLine(fileCity);

	}

	public List<String> makeListLineByLine(InputStream is) {
		List<String> lst = new ArrayList<String>();
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line = br.readLine()) != null) {
				lst.add(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lst;
	}

	public String stripAccents(String s) {
		s = Normalizer.normalize(s, Normalizer.Form.NFD);
		s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		return s;
	}

	public String randomPhone() {
		StringBuilder sBuilder = new StringBuilder(phoneHeader[random.nextInt(phoneHeader.length)]);
		for (int i = 0; i < 7; i++) {
			sBuilder.append(random.nextInt(10));
		}
		return sBuilder.toString();
	}

	public String randomEmail() {
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			String v = emailList.get(random.nextInt(emailList.size()));
			sBuilder.append(stripAccents(v.toLowerCase()));
			if (i < 2)
				sBuilder.append("_");
		}
		sBuilder.append(emailTypes[random.nextInt(emailTypes.length)]);
		return sBuilder.toString();
	}

	public String randomMaritalStatus(Date birthday) {
		Calendar calendar = DateUtils.toCalendar(birthday);
		Calendar calendarNow = DateUtils.toCalendar(new java.util.Date());
		int age = calendarNow.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
		int percent = random.nextInt(101);
		if (percent <= 70)
			return "single";
		else if (percent <= 85 && age >= 28)
			return "divorced";
		else if (percent <= 90 && age >= 40)
			return "widowed";
		else if (percent <= 95 && age >= 30)
			return "separated";
		else if (percent <= 100 && age >= 25)
			return "maried";
		else
			return "single";
	}

	public User randomUser() {
		long offset = Date.valueOf("1997-01-01").getTime();
		long end = Date.valueOf("1947-01-01").getTime();
		long diff = end - offset + 1;
		Date birthdayRand = new Date(offset + (long) (random.nextDouble() * diff));

		User user = new User();
		user.setVerified(true);
		user.setGender(gender[random.nextInt(gender.length)]);
		user.setAvatarPhoto("male".equals(user.getGender()) ? "default_male_avatar.jpg" : "default_female_avatar.jpg");
		user.setBirthday(birthdayRand);
		user.setExpiries(Timestamp.valueOf("2050-01-01 00:00:00"));
		user.setPassword("12345678");
		user.setFirstName(nameList.get(random.nextInt(nameList.size())) + " "
				+ nameList.get(random.nextInt(nameList.size())));
		user.setLastName(nameList.get(random.nextInt(nameList.size())));
		user.setName(user.getLastName() + " " + user.getFirstName());
		user.setEmail(randomEmail());
		user.setContactNumber(randomPhone());
		user.setMaritalStatus(randomMaritalStatus(user.getBirthday()));
		user.setHometown(cityList.get(random.nextInt(cityList.size())));
		user.setCountryside("Việt Nam");
		user.setHeight(random.nextInt(41) + 150);
		user.setWeight(random.nextInt(33) + 48);
		user.setRegMethod("native");
		user.setLocale("vi_VN");
		user.setCreateAt(new Timestamp(System.currentTimeMillis()));
		return user;
	}

	public static void main(String[] args) {
		UserMaker maker = new UserMaker();

		for (int i = 0; i < 200; i++) {
			User userRand = maker.randomUser();
			try {
				System.out.println("Adding " + userRand.getLastName() + " " + userRand.getFirstName());
				UserDAO.register(userRand);
				System.out.println("OK");
			} catch (EmailAlready e) {
				System.out.println(e);
			} catch (ContactNumberAlready e) {
				System.out.println(e);
			}
		}
	}
}
