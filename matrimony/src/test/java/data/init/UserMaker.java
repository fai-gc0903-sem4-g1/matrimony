/**
 * 
 */
package data.init;

import java.io.BufferedReader;
import java.io.File;
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

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.matrimony.database.UserDAO;
import com.matrimony.entity.User;
import com.matrimony.exception.STException.ContactNumberAlready;
import com.matrimony.exception.STException.EmailAlready;
import com.matrimony.model.Convention;
import com.matrimony.model.UploadImageToServer;

/**
 * @author SON
 *
 */
public class UserMaker {
	private final String avatarFolderServerPath="E:\\workspace\\eclipse\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\matrimony\\resources\\profile\\avatar";
	private final String dataFolderPath = System.getProperty("user.home") + "/Desktop/matrimony_init";
	private Random random = new Random();
	private final String[] phoneHeader = { "096", "0167", "098" };
	private final String[] gender = { Convention.FEMALE, Convention.MALE };
	private final String[] emailTypes = { "@yahoo.com", "@yahoo.com.vn", "@gmail.com", "@gmail.com.vn" };
	private File[] avatarFemaleArray, avatarMaleArray;
	private List<String> nameList, emailList, cityList;

	/**
	 * 
	 */
	public UserMaker() {
		FileInputStream fileName = null, fileEmail = null, fileCity = null;
		try {
			fileName = new FileInputStream(dataFolderPath + "/name.txt");
			fileEmail = new FileInputStream(dataFolderPath + "/email.txt");
			fileCity = new FileInputStream(dataFolderPath + "/city.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nameList = makeListLineByLine(fileName);
		emailList = makeListLineByLine(fileEmail);
		cityList = makeListLineByLine(fileCity);

		avatarFemaleArray = new File(dataFolderPath + "/avatar_female").listFiles();
		avatarMaleArray = new File(dataFolderPath + "/avatar_male").listFiles();
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
			return "SINGLE";
		else if (percent <= 85 && age >= 28)
			return "DIVORCED";
		else if (percent <= 90 && age >= 40)
			return "WIDOWED";
		else if (percent <= 95 && age >= 30)
			return "SEPARATED";
		else if (percent <= 100 && age >= 25)
			return "MARIED";
		else
			return "single";
	}

	public String randomAvatar(String gender){
		String obfName=RandomStringUtils.randomAlphanumeric(26)+".jpg";
		File file;
		if(Convention.FEMALE.equals(gender))
		file=avatarFemaleArray[random.nextInt(avatarFemaleArray.length)];
		else
			file=avatarMaleArray[random.nextInt(avatarMaleArray.length)];
		UploadImageToServer imageToServer=new UploadImageToServer();
		try {
			imageToServer.upload(avatarFolderServerPath+"/"+obfName, new FileInputStream(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		return obfName;
	}
	public User randomUser() {
		long offset = Date.valueOf("1997-01-01").getTime();
		long end = Date.valueOf("1947-01-01").getTime();
		long diff = end - offset + 1;
		Date birthdayRand = new Date(offset + (long) (random.nextDouble() * diff));

		User user = new User();
		user.setVerified(true);
		user.setGender(gender[random.nextInt(gender.length)]);
		user.setAvatarPhoto(randomAvatar(user.getGender()));
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
		user.setRegMethod("NATIVE");
		user.setReligion("");
		user.setLocale("vi_VN");
		user.setCreateAt(new Timestamp(System.currentTimeMillis()));
		user.setIntroduce("Mình rất vui được làm quen với bạn!");
		return user;
	}

	public static void main(String[] args) {
		UserMaker maker = new UserMaker();
		System.out.println(maker.randomUser().getExpiries());
		for (int i = 0; i < 200; i++) {
			User userRand = maker.randomUser();
			try {
				System.out.println("Adding " + userRand.getLastName() + " " + userRand.getFirstName() +" gender: "+userRand.getGender() );
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
