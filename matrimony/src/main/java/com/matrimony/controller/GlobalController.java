/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javassist.expr.NewArray;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.matrimony.database.UserDAO;
import com.matrimony.entity.User;
import com.matrimony.exception.STException;
import com.matrimony.util.MailUtil;

/**
 *
 * @author SON
 */
@Controller
public class GlobalController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewIndex() {
		return "index";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String doIndex(HttpServletResponse response, HttpServletRequest request, String process,
			@Valid @ModelAttribute("userLogin") User userLogin, BindingResult loginBinding, String keepLoggin,
			@Valid @ModelAttribute("userReg") User userReg, BindingResult registerBinding, String day, String month,
			String year) {
		if ("login".equals(process)) {
			if (loginBinding.hasFieldErrors("username") || loginBinding.hasFieldErrors("password")) {
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
				} else
					return "active";
			} catch (STException.UsernameNotExist ex) {
				System.out.println(ex);
				request.setAttribute("notice",
						"Tài khoản không tồn tại, chúng tôi k tìm thấy tên tài khoản, email hay số điện thoại");
			} catch (STException.WrongPassword ex) {
				System.out.println(ex);
				request.setAttribute("notice", "Sai password");
			}
			return "index";
		} else if ("register".equals(process)) {
			Date birthday = null;
			try {
				birthday = Date.valueOf(year + "-" + month + "-" + day);
			} catch (IllegalArgumentException ex) {
				System.out.println(ex + ": Date not correct");
				request.setAttribute("birthdayValid", "Ngày tháng chọn sai");
			}
			if (registerBinding.hasErrors() || birthday == null) {
				System.out.println("Form register error");
				return "index";
			}
			System.out.println("Form register OK");
			userReg.setRegistrationTime(new Timestamp(System.currentTimeMillis()));
			userReg.setRegistrationIP(request.getRemoteAddr());
			String activeKey = RandomStringUtils.randomAlphanumeric(10);

			userReg.setActiveKey(activeKey);
			userReg.setRegMethod("native");
			userReg.setBirthday(birthday);
			try {
				UserDAO.add(userReg);
				// make username
				User user = UserDAO.findByEmail(userReg.getEmail());
				user.setUsername(user.getUserId());
				UserDAO.Update(user);
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
		} else
			return "index";
	}

	

	@RequestMapping(value = "{username}")
	public String doProfile(@PathVariable("username") String username, Model model) {
		System.out.println(username);
		User user = UserDAO.findByUsername(username);
		if (user != null) {
			System.out.println(user.isVerified());
		}
		if (user != null && user.isVerified()) {
			model.addAttribute("user", user);
			return "profile";
		} else {
			return "userNotFound";
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
	
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String viewTest() {
		return "test";
	}
	@RequestMapping(value = "test", method = RequestMethod.POST)
	public String doTest(HttpServletRequest request){
		System.out.println("ngon ");
		String avatarFolderPath = request.getServletContext().getRealPath("/resources/profile/avatar");
		System.out.println(avatarFolderPath);
		System.out.println(new File(avatarFolderPath).exists());
//		File repo = new File(repoPath+"");
//		if (!repo.exists())
//			repo.mkdir();
		try {
			Collection<Part> parts = request.getParts();
			for (Part part : parts) {
				String fileName = part.getSubmittedFileName();
				if (fileName != null) {
					InputStream is = part.getInputStream();
					String fileNameGenerated=RandomStringUtils.randomAlphanumeric(26);
					File file = new File(avatarFolderPath+"/"+fileNameGenerated);
					FileOutputStream fileOutputStream = new FileOutputStream(file);
					System.out.println(fileName);
					int buffer;
					while ((buffer = is.read()) != -1) {
						fileOutputStream.write(buffer);
					}
					fileOutputStream.close();
					User user=(User) request.getSession().getAttribute("user");
					user.setAvatarPhoto(fileNameGenerated);
					UserDAO.Update(user);
				}
			}
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
		return "test";
	}
	
//	public String uploadImg(Collection<Part> collection) throws IOException {
//		StringBuilder imageSrc = new StringBuilder();
//		for (Part part : collection) {
//			InputStream is = part.getInputStream();
//			String fileName = part.getSubmittedFileName();
//			if (is instanceof FileInputStream && fileName != null) {
//				String filePath = getServletContext().getRealPath("/resources") + "/img/img_product_uploaded/" + fileName;
//				String imgLinkSQL = "/ShoppingAssignment/resources/img/img_product_uploaded/" + fileName;
//				FileOutputStream file = new FileOutputStream(filePath);
//				int bye;
//				while ((bye = is.read()) != -1) {
//					file.write(bye);
//				}
//				file.close();
//				is.close();
//
//				imageSrc.append(imgLinkSQL + ";");
//			}
//		}
//		if (imageSrc.length() > 0)
//			imageSrc.deleteCharAt(imageSrc.length() - 1);
//		return imageSrc.toString();
//	}
}
