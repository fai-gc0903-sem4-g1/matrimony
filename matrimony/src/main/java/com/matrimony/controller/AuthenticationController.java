/**
 * 
 */
package com.matrimony.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.matrimony.database.UserDAO;
import com.matrimony.entity.User;
import com.matrimony.exception.STException;
import com.matrimony.exception.STException.ContactNumberAlready;
import com.matrimony.exception.STException.EmailAlready;
import com.matrimony.util.MailUtil;

import facebook.api.FBConnection;
import facebook.api.FBGraph;
import facebook.entity.FBProfile;

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
	public String doLogin(HttpServletResponse response, HttpServletRequest request,
			@Valid @ModelAttribute("userLogin") User userLogin, BindingResult bindingResult, String keepLoggin) {
		if (bindingResult.hasFieldErrors("password")) {
			System.out.println("form login error");
			request.setAttribute("loginFormError", true);
			return "index";
		}
		try {
			User user = UserDAO.login(userLogin.getUsername(), userLogin.getPassword());
			user.setLoginTime(new Timestamp(System.currentTimeMillis()));
			user.setIpLogin(request.getRemoteAddr());
			System.out.println("Login: password hashed: " + user.getPassword());
			UserDAO.Update(user);

			request.getSession().setAttribute("user", user);
			System.out.println(user.getEmail() + " logged in");
			if (user.isVerified()) {
				if (keepLoggin != null) {
					keepMeLoggedIn(response, userLogin);
				}
				return "redirect:";
			} else {
				return "active";
			}

		} catch (STException.UsernameNotExist ex) {
			System.out.println(ex);
			request.setAttribute("notice",
					"Tài khoản không tồn tại, chúng tôi k tìm thấy tên tài khoản, email hay số điện thoại");
		} catch (STException.WrongPassword ex) {
			System.out.println(ex);
			request.setAttribute("notice", "Sai mật khẩu");
		}
		return "index";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String doRegister(HttpServletRequest request, @Valid @ModelAttribute("userReg") User userReg,
			BindingResult bindingResult, String day, String month, String year, String reEmail) {
		Date birthday = null;
		boolean ageEnought=false, twinEmail=false;
		try {
			birthday = Date.valueOf(year + "-" + month + "-" + day);
			int thisYear =0, bornYear=0;
			thisYear= DateUtils.toCalendar(new java.util.Date(System.currentTimeMillis())).get(Calendar.YEAR);
			bornYear = DateUtils.toCalendar(birthday).get(Calendar.YEAR);
			if(thisYear-bornYear<18){
				ageEnought=false;
				request.setAttribute("birthdayNotEnough", "Bạn chưa đủ tuổi thể tham gia!");
			}else ageEnought=true;
		} catch (IllegalArgumentException ex) {
			System.out.println("Register: " + ex.getMessage());
			request.setAttribute("birthdayInvalid", "Ngày tháng chọn sai");
		}
		if(reEmail.equals(userReg.getEmail()))
				twinEmail=true;
		else request.setAttribute("reEmailInvalid", "2 email không giống nhau");
		if (bindingResult.hasFieldErrors("firstName") || bindingResult.hasFieldErrors("lastName")
				|| bindingResult.hasFieldErrors("contactNumber") || bindingResult.hasFieldErrors("email")
				|| bindingResult.hasFieldErrors("gender") || null == birthday || !ageEnought || !twinEmail) {
			System.out.println("Register: Form register error");
			request.setAttribute("registerFormError", true);
			return "index";
		}
		System.out.println("Register: Form register OK");
		userReg.setBirthday(birthday);
		User userBuilt = buildNewUser(userReg, request);
		try {
			UserDAO.add(userBuilt);
			User tempUser = UserDAO.findByEmail(userBuilt.getEmail());
			tempUser.setUsername(userBuilt.getUserId());
			UserDAO.Update(tempUser);

			sendMailActive(userBuilt.getEmail(), userBuilt.getActiveKey());
			request.getSession().setAttribute("user", UserDAO.findByEmail(userBuilt.getEmail()));
		} catch (STException.EmailAlready ex) {
			System.out.println(ex.getMessage());
			request.setAttribute("notice", "EmailAlready");
			request.setAttribute("registerRespCode", 1);
		} catch (STException.ContactNumberAlready ex) {
			System.out.println(ex.getMessage());
			request.setAttribute("notice", "ContactNumberAlready");
			request.setAttribute("registerRespCode", 2);
		}
		return "index";
	}

	@RequestMapping(value = "active", method = RequestMethod.POST)
	public String doActive(HttpServletRequest request, String activeKey) {
		System.out.println(activeKey);
		User curuser = (User) request.getSession().getAttribute("user");
		System.out.println(curuser.getEmail() + " want to active");
		if (curuser.getActiveKey().equals(activeKey)) {
			curuser.setVerified(true);
			curuser.setVerifiedTime(new Timestamp(System.currentTimeMillis()));
			UserDAO.Update(curuser);
			System.out.println(curuser.getEmail() + " activated");
			return "redirect:";
		} else {
			return "active";
		}
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
		session.setAttribute("user", null);
		return "redirect:";
	}

	@RequestMapping(value = "FBRedirect", method = RequestMethod.GET)
	public String FBRedirect(HttpServletRequest request, String code, HttpSession session, HttpServletResponse response) {
		FBConnection fBConnection = new FBConnection();
		try {
			String accessToken = fBConnection.getAccessToken(code);
			System.out.println(accessToken);
			FBGraph fBGraph = new FBGraph();
			FBProfile fbProfile = fBGraph.getFBProfile(accessToken);
			System.out.println("Login with facebook" + fbProfile);
			User userRegUsingFB = new User();
			userRegUsingFB.setEmail(fbProfile.getEmail());
			userRegUsingFB.setVerified(true);// verified always true
			userRegUsingFB.setFirstName(fbProfile.getFirst_name());
			userRegUsingFB.setLastName(fbProfile.getLast_name());
			userRegUsingFB.setGender(fbProfile.getGender());
			userRegUsingFB.setLocale(fbProfile.getLocale());
			userRegUsingFB.setRegMethod("Facebook");
			userRegUsingFB.setSocialNetwork(fbProfile.getLink());
			userRegUsingFB.setContactNumber("");
			userRegUsingFB.setName(fbProfile.getName());

			userRegUsingFB.setAvatarPhoto(userRegUsingFB.getGender().equals("male") ? "default_male_avatar.jpg"
					: "default_female_avatar.jpg");
			userRegUsingFB.setRegistrationTime(new Timestamp(System.currentTimeMillis()));
			userRegUsingFB.setRegistrationIP(request.getRemoteAddr());

			System.out.println("Login with facebook Create user from facebook OK");
			request.getSession().setAttribute("userRegUsingFB", userRegUsingFB);
			User checkUser = UserDAO.findByEmail(userRegUsingFB.getEmail());
			if (checkUser == null) {
				request.setAttribute("fbResp", 1);
			} else {
				request.setAttribute("fbResp", 0);
				System.out.println("Login with facebook: User have already");
			}
			return "index";
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			return "userNotFound";
		}
	}

	@RequestMapping(value = "loginWithFacebook", method = RequestMethod.POST)
	public String doSaveUserFB(HttpServletRequest request, @ModelAttribute("logginFBUser") User user,
			BindingResult bindingResult) {
		System.out.println(user.getPassword());
		if (bindingResult.hasFieldErrors("password")) {
			request.setAttribute("fbPass", true);
			request.setAttribute("fbResp", 1);
			System.out.println("loginWithFacebook: form error");
			return "index"; // purpose join us
		} else {
			System.out.println("loginWithFacebook: form OK");
			User userRegUsingFB = (User) request.getSession().getAttribute("userRegUsingFB");
			if (userRegUsingFB != null) {
				userRegUsingFB.setPassword(user.getPassword());
				try {
					UserDAO.add(userRegUsingFB);
					User tempUser = UserDAO.findByEmail(userRegUsingFB.getEmail());
					tempUser.setUsername(tempUser.getUserId());
					UserDAO.Update(tempUser);
				} catch (EmailAlready e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ContactNumberAlready e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "index"; // purpose home
			} else {
				return "exception";
			}
		}
	}

	@RequestMapping(value = "settings", method = RequestMethod.POST)
	public String viewSettings(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		try {
			System.out.println(user);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "settings";
	}

	@RequestMapping(value = "resend", method = RequestMethod.POST)
	public String resend(HttpSession session) {
		User user = (User) session.getAttribute("user");
		sendMailActive(user.getEmail(), user.getActiveKey());
		return "active";
	}

	public User buildNewUser(User user, HttpServletRequest request) {
		String activeKey = RandomStringUtils.randomAlphanumeric(10);
		user.setRegistrationTime(new Timestamp(System.currentTimeMillis()));
		user.setRegistrationIP(request.getRemoteAddr());
		user.setActiveKey(activeKey);
		user.setRegMethod("native");
		user.setAvatarPhoto(user.getGender().equals("male") ? "default_male_avatar.jpg" : "default_female_avatar.jpg");
		user.setName(user.getFirstName() + " " + user.getLastName());
		return user;
	}

	public void keepMeLoggedIn(HttpServletResponse response, User user) {
		Cookie[] cookies = new Cookie[3];
		cookies[0] = new Cookie("loginName", user.getEmail());
		cookies[1] = new Cookie("password", "");
		cookies[2] = new Cookie("keepLoggin", "true");
		for (Cookie c : cookies) {
			c.setMaxAge(60 * 60 * 24 * 365);
			response.addCookie(c);
		}
		System.out.println("saved cookie");
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
