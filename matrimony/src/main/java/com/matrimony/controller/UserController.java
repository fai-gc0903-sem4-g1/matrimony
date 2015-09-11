/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.controller;

import com.matrimony.database.UserDAO;
import com.matrimony.entity.User;
import com.matrimony.exception.STException;
import com.matrimony.util.MailUtil;

import facebook.api.FBConnection;
import facebook.api.FBGraph;
import facebook.entity.FBProfile;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author SON
 */
@Controller
public class UserController {

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String doLogin(HttpServletResponse response, HttpServletRequest request,
			@Valid @ModelAttribute("userLogin") User userLogin, BindingResult bindingResult, String keepLoggin) {
		if (bindingResult.hasFieldErrors("username") || bindingResult.hasFieldErrors("password")) {
			System.out.println("form login error");
			return "index";
		}
		try {
			User account = UserDAO.login(userLogin.getUsername(), userLogin.getPassword());
			account.setLastTimeLogin(new Timestamp(System.currentTimeMillis()));
			account.setLastIPLogin(request.getRemoteAddr());
			UserDAO.Update(account);

			request.getSession().setAttribute("user", account);
			System.out.println(account.getEmail() + " logged in");
			if (account.isVerified()) {
				if (keepLoggin != null) {
					Cookie[] cookies = new Cookie[3];
					cookies[0] = new Cookie("loginName", userLogin.getUsername());
					cookies[1] = new Cookie("password", userLogin.getPassword());
					cookies[2] = new Cookie("keepLoggin", "true");
					for (Cookie c : cookies) {
						c.setMaxAge(60 * 60 * 24 * 365);
						response.addCookie(c);
					}
				}
				return "redirect:";
			} else {
				return "active";
			}

		} catch (STException.UsernameNotExist ex) {
			Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
			request.setAttribute("notice",
					"Tài khoản không tồn tại, chúng tôi k tìm thấy tên tài khoản, email hay số điện thoại");
		} catch (STException.WrongPassword ex) {
			Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
			request.setAttribute("notice", "Sai password");
		}
		return "index";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String doRegister(HttpServletRequest request, @Valid @ModelAttribute("userReg") User userReg,
			BindingResult bindingResult, String day, String month, String year) {
		System.out.println(userReg);
		Date birthday = null;
		if (bindingResult.hasErrors()) {
			try {
				birthday = Date.valueOf(year + "-" + month + "-" + day);
			} catch (IllegalArgumentException ex) {

				System.out.println(ex + ": Date not correct");
				request.setAttribute("birthdayValid", "Ngày tháng chọn sai");
			}
			System.out.println("Form register error");
			return "index";
		}
		System.out.println("Form register OK");
		userReg.setRegistrationTime(new Timestamp(System.currentTimeMillis()));
		userReg.setRegistrationIP(request.getRemoteAddr());
		String activeKey = UUID.randomUUID().toString().toUpperCase(); // Generate
																		// key
																		// active
		userReg.setActiveKey(activeKey);
		userReg.setRegMethod("native");
		userReg.setBirthday(birthday);
		try {
			UserDAO.add(userReg);
			sendMailActive(userReg.getEmail(), activeKey);
			request.getSession().setAttribute("user", UserDAO.findByEmail(userReg.getEmail()));
			return "active";
		} catch (STException.EmailAlready ex) {
			System.out.println(ex.getMessage());
			request.setAttribute("notice", "EmailAlready");
			return "failed";
		} catch (STException.ContactNumberAlready ex) {
			System.out.println(ex.getMessage());
			request.setAttribute("notice", "ContactNumberAlready");
			return "failed";
		}
	}

	@RequestMapping(value = "active", method = RequestMethod.POST)
	public String doActive(HttpServletRequest request, String activeKey) {
		System.out.println(activeKey);
		User curAccount = (User) request.getSession().getAttribute("user");
		System.out.println(curAccount.getEmail() + " want to active");
		if (curAccount.getActiveKey().equals(activeKey)) {
			curAccount.setVerified(true);
			curAccount.setVerifiedTime(new Timestamp(System.currentTimeMillis()));
			UserDAO.Update(curAccount);
			System.out.println(curAccount.getEmail() + " activated");
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
	public String FBRedirect(String code, HttpSession session, HttpServletResponse response) {
		FBConnection fBConnection = new FBConnection();
		try {
			String accessToken = fBConnection.getAccessToken(code);
			System.out.println(accessToken);
			FBGraph fBGraph = new FBGraph();
			FBProfile fbProfile = fBGraph.getFBProfile(accessToken);
			System.out.println(fbProfile);
			User userFBReg = new User();
			userFBReg.setEmail(fbProfile.getEmail());
			userFBReg.setVerified(fbProfile.isVerified());
			userFBReg.setFirstName(fbProfile.getFirst_name());
			userFBReg.setLastName(fbProfile.getLast_name());
			userFBReg.setGender(fbProfile.getGender());
			userFBReg.setLocale(fbProfile.getLocale());
			userFBReg.setRegMethod("Facebook");
			userFBReg.setSocialNetwork(fbProfile.getLink());
			userFBReg.setContactNumber("");
			System.out.println("Create user from facebook OK");
			try {
				UserDAO.add(userFBReg);
				System.out.println("Added user " + userFBReg.getEmail());
				Cookie[] cookies = new Cookie[3];
				cookies[0] = new Cookie("loginName", userFBReg.getEmail());
				cookies[1] = new Cookie("password", "");
				cookies[2] = new Cookie("keepLoggin", "true");
				for (Cookie c : cookies) {
					c.setMaxAge(60 * 60 * 24 * 365);
					response.addCookie(c);
				}
				System.out.println("saved cookie");
				session.setAttribute("user", UserDAO.findByEmail(userFBReg.getEmail()));
				return "redirect:";
			} catch (STException.EmailAlready ex) {
				System.out.println("Hinh nhu ban do co tai khoan roi");
			} catch (STException.ContactNumberAlready ex) {
				System.out.println("Hinh nhu ban da co so dien thoai");
			}
			return "failed";
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			return "userNotFound";
		}
	}

	@RequestMapping(value = "{username}")
	public String doProfile(@PathVariable("username") String username, Model model) {
		System.out.println(username);
		User account = UserDAO.findByUsername(username);
		if (account == null) {
			return "userNotFound";
		} else {
			model.addAttribute("account", account);
			return "fbProfileile";
		}
	}

	@RequestMapping(value = "settings", method = RequestMethod.POST)
	public String viewSettings(HttpServletRequest request) {
		System.out.println("sau day la session");
		User account = (User) request.getSession().getAttribute("account");
		try {
			System.out.println(account);
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

	@RequestMapping(value = "recover", method = RequestMethod.GET)
	public String viewRecover() {
		return "recoverUser";
	}

	@RequestMapping(value = "recover", method = RequestMethod.POST)
	public String doStringRecover(HttpServletRequest request, String email, String process) {
		System.out.println("process " + process);
		if ("level1".equals(process)) {
			User user = UserDAO.findByEmail(email);
			if (user == null) {
				System.out.println("Quen mat khau: khong tim thay email");
				request.setAttribute("code", 0);
			} else {
				System.out.println("Quen mat khau: tim thay email");
				String code = recoverUser(user);
				request.getSession().setAttribute("codeRecover", code);
				request.setAttribute("code", 1);
			}
		}
		return "recoverUser";
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

	public String recoverUser(User user) {
		String code = RandomStringUtils.randomAlphanumeric(8);
		String sub = "Khôi phục tài khoản";
		StringBuilder cont = new StringBuilder();
		cont.append("Mã: ");
		cont.append(code);
		cont.append("\n");
		cont.append("Cam on da su dung dich vu cua chung toi!");
		MailUtil mail = new MailUtil(user.getEmail(), sub, cont.toString());
		mail.send();
		return code;
	}

}
