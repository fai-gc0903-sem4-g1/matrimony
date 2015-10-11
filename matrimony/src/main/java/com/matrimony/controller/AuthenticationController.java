/**
 * 
 */
package com.matrimony.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.facebook.api.FBConnection;
import com.facebook.api.FBGraph;
import com.facebook.entity.UserProfile;
import com.matrimony.database.UserDAO;
import com.matrimony.entity.User;
import com.matrimony.exception.STException;
import com.matrimony.exception.STException.ContactNumberAlready;
import com.matrimony.exception.STException.EmailAlready;
import com.matrimony.exception.STException.NotNativeAccount;
import com.matrimony.model.Convention;
import com.matrimony.model.Regex;
import com.matrimony.model.SessionKey;
import com.matrimony.model.StringResouces;
import com.matrimony.util.MailUtil;

/**
 * @author SON
 *
 */
@Controller
public class AuthenticationController {
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String viewLogin() {
		return "redirect:";
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String viewRegister() {
		return "redirect:";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String doLogin(HttpServletResponse response, HttpServletRequest request, String login, String password,
			String keepLoggin) {
		if (login == null || password == null) {
			return "comotsucokhonghenhe";
		}
		boolean wellForm = true;
		StringResouces sr = new StringResouces(StringResouces.vi_VN);
		if ("".equals(login)) {
			wellForm = false;
			request.setAttribute("loginNameInvalid", sr.getData().get("loginNameNotEmpty"));
		}
		if (!Pattern.matches(Regex.PASSWORD, password)) {
			wellForm = false;
			request.setAttribute("loginPasswordInvalid", sr.getData().get("loginPasswordInvalid"));
		}

		if (!wellForm)
			return "joinUs";

		try {
			User userLogin = new User();
			userLogin.setUsername(login);
			userLogin.setPassword(password);
			// userLogin.setLoginTime(new
			// Timestamp(System.currentTimeMillis()));
			// userLogin.setIpLogin(request.getRemoteAddr());
			User user = UserDAO.login(userLogin);
			request.getSession().setAttribute(SessionKey.USER, user);
			if (user.isVerified()) {
				if (keepLoggin != null) {
					keepMeLoggedIn(response, user);
				}
				return "redirect:";
			} else {
				return "active";
			}

		} catch (STException.UserNotExists ex) {
			System.out.println(ex);
			request.setAttribute("loginNameInvalid", sr.getData().get("loginUserNotExists"));
		} catch (STException.WrongPassword ex) {
			System.out.println(ex);
			request.setAttribute("loginPasswordInvalid", sr.getData().get("loginWrongPassword"));
		} catch (NotNativeAccount e) {
			System.out.println(e);
			request.setAttribute("loginNameInvalid", sr.getData().get("loginNotNativeAccount"));
		}
		request.setAttribute("login", login);
		request.setAttribute("password", password);
		return "joinUs";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String doRegister(HttpServletRequest request, User userReg, String day, String month, String year,
			String reEmail) {
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		System.out.print(currentTime + ": " + userReg.getGender());
		if (userReg.getFirstName() == null || userReg.getLastName() == null || userReg.getEmail() == null
				|| userReg.getGender() == null) {
			return "cogidokhongdung";
		}
		boolean wellForm = true;
		StringResouces sr = new StringResouces(StringResouces.vi_VN);
		if (!Pattern.matches(Regex.NAME, userReg.getFirstName())) {
			wellForm = false;
			request.setAttribute("regFirstNameInvalid", sr.getData().get("regFirstNameInvalid"));
		}
		if (!Pattern.matches(Regex.NAME, userReg.getLastName())) {
			wellForm = false;
			request.setAttribute("regLastNameInvalid", sr.getData().get("regLastNameInvalid"));
		}
		if (!Pattern.matches(Regex.PASSWORD, userReg.getPassword())) {
			wellForm = false;
			request.setAttribute("regPasswordInvalid", sr.getData().get("regPasswordInvalid"));
		}
		if (!Pattern.matches(Regex.GENDER, userReg.getGender())) {
			wellForm = false;
			request.setAttribute("regGenderInvalid", sr.getData().get("regGenderInvalid"));
		}
		if (!Pattern.matches(Regex.PHONE, userReg.getFirstName()) && "".equals(userReg.getContactNumber())) {
			wellForm = false;
			request.setAttribute("regPhoneInvalid", sr.getData().get("regPhoneInvalid"));
		}
		Date birthday = null;
		try {
			birthday = Date.valueOf(year + "-" + month + "-" + day);
			if (DateUtils.toCalendar(currentTime).get(Calendar.YEAR)
					- DateUtils.toCalendar(birthday).get(Calendar.YEAR) < 18) {
				wellForm = false;
				request.setAttribute("regBirthdayInvalid", sr.getData().get("regNotEnoughAge"));
			}
		} catch (IllegalArgumentException ex) {
			System.out.println("Register: " + ex.getMessage());
			wellForm = false;
			request.setAttribute("regBirthdayInvalid", "Ngày tháng ch�?n sai");
		}

		if (!wellForm)
			return "joinUs";

		String activeKey = RandomStringUtils.randomAlphanumeric(8);
		userReg.setBirthday(birthday);
		userReg.setRegistrationIP(request.getRemoteAddr());
		userReg.setActiveKey(activeKey);
		userReg.setRegMethod("Native");
		userReg.setAvatarPhoto(userReg.getGender().equals(Convention.FEMALE) ? Convention.DEFAULT_FEMALE_IMG_AVATAR
				: Convention.DEFAULT_MALE_IMG_AVATAR);
		userReg.setName(userReg.getFirstName() + " " + userReg.getLastName());
		userReg.setExpiries(currentTime);
		try {
			User userFromDB = UserDAO.register(userReg);
			sendMailActive(userReg.getEmail(), userReg.getActiveKey());
			request.getSession().setAttribute(SessionKey.USER, userFromDB);
			return "active";
		} catch (STException.EmailAlready ex) {
			System.out.println(ex.getMessage());
			request.setAttribute("regEmailAlready", sr.getData().get("regEmailAlready"));
		} catch (STException.ContactNumberAlready ex) {
			System.out.println(ex.getMessage());
			request.setAttribute("regPhoneAlready", sr.getData().get("regPhoneAlready"));
		}
		return "joinUs";
	}

	@RequestMapping(value = "logout", method = RequestMethod.POST)
	public String doLogout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		Cookie[] allCookie = request.getCookies();
		for (Cookie c : allCookie) {
			if ("keepLoggin".equals(c.getName())) {
				c.setValue("false");
				response.addCookie(c);
			}
		}
		session.setAttribute(SessionKey.USER, null);
		return "redirect:";
	}

	@RequestMapping(value = "active", method = RequestMethod.POST)
	public String doActive(HttpServletRequest request, String activeKey) {
		System.out.println(activeKey);
		User ssUser = (User) request.getSession().getAttribute(SessionKey.USER);
		System.out.println(ssUser.getEmail() + " want to active");
		if (ssUser.getActiveKey().equals(activeKey)) {
			ssUser.setVerified(true);
			ssUser.setVerifiedTime(new Timestamp(System.currentTimeMillis()));
			UserDAO.Update(ssUser);
			System.out.println(ssUser.getEmail() + " activated");
			return "redirect:";
		} else {
			return "active";
		}
	}

	@RequestMapping(value = "fbredirect", method = RequestMethod.GET)
	public String fbredirect(HttpServletRequest request, HttpServletResponse response, String code) {
		FBConnection conn = new FBConnection();
		try {
			String accessToken = conn.getAccessToken(code);
			UserProfile userProfile = FBGraph.gatherUserProfile(accessToken);
			User user = new User();
			user.setFirstName(userProfile.getFirstName());
			user.setLastName(userProfile.getLastName());
			user.setEmail(userProfile.getEmail());
			user.setVerified(true);
			user.setName(userProfile.getName());
			user.setGender(userProfile.getGender());
			user.setRegMethod("Facebook");
			user.setExpiries(new Timestamp(System.currentTimeMillis()));
			// InputStream image =
			// FBGraph.gatherAvatarImage(userProfile.getId());
			// String avatarPath =
			// request.getServletContext().getRealPath("/resoucres/profile/avatar");
			// String newFileName = RandomStringUtils.randomAlphanumeric(26);
			// UploadToServer.upFile(avatarPath + "/" + newFileName + ".jpg",
			// image);
			// user.setAvatarPhoto(newFileName);
			user.setAvatarPhoto(user.getGender().equals(Convention.FEMALE) ? Convention.DEFAULT_FEMALE_IMG_AVATAR
					: Convention.DEFAULT_MALE_IMG_AVATAR);
			User ssUser = null;
			try {
				ssUser = UserDAO.register(user);
			} catch (EmailAlready | ContactNumberAlready e) {
				request.setAttribute("accAlready", "true");
				System.out.println(e);
				ssUser = UserDAO.findByEmail(user.getEmail());
			}
			keepMeLoggedIn(response, ssUser);
			request.getSession().setAttribute("user", ssUser);
			return "redirect:";
		} catch (IOException e) {
			System.out.println("fbredirect gap exception");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:";
	}

	@RequestMapping(value = "settings", method = RequestMethod.POST)
	public String viewSettings(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(SessionKey.USER);
		try {
			System.out.println(user);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "settings";
	}

	@RequestMapping(value = "resend", method = RequestMethod.POST)
	public String resend(HttpSession session) {
		User user = (User) session.getAttribute(SessionKey.USER);
		sendMailActive(user.getEmail(), user.getActiveKey());
		return "active";
	}

	public void keepMeLoggedIn(HttpServletResponse response, User user) {
		Cookie[] cookies = new Cookie[5];
		cookies[0] = new Cookie("id", user.getId());
		cookies[1] = new Cookie("login", user.getUsername());
		cookies[2] = new Cookie("password", user.getPassword());
		cookies[3] = new Cookie("keepLoggin", "true");
		cookies[4] = new Cookie("type", user.getRegMethod());
		for (Cookie c : cookies) {
			c.setMaxAge(60 * 60 * 24 * 365);
			System.out.println("added cookie: " + c.getName());
			response.addCookie(c);
		}
	}

	public void sendMailActive(String email, String key) {
		String sub = "Chao mung den voi matrimony, kich hoat tai khoan";
		StringBuilder cont = new StringBuilder();
		cont.append("Day la key active: ");
		cont.append(key);
		cont.append("\n");
		cont.append("Cam on da su dung dich vu cua chung toi!");
		MailUtil mail = new MailUtil(email, sub, cont.toString());
		mail.send();
	}

}
