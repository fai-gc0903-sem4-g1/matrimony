/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.matrimony.database.UserDAO;
import com.matrimony.entity.User;
import com.matrimony.security.HashUtil;
import com.matrimony.util.MailUtil;

/**
 *
 * @author SON
 */
@Controller
public class RecoverController {

	@RequestMapping(value = "recover", method = RequestMethod.GET)
	public String viewRecover() {
		return "recoverUser";
	}

	@RequestMapping(value = "recover", method = RequestMethod.POST)
	public String doStringRecover(HttpServletRequest request, String textField, String process,
			@ModelAttribute("recoverUser") User ruser, BindingResult bindingResult) {
		if ("level1".equals(process)) {
			System.out.println("process " + process);
			User userRecover = UserDAO.findByEmail(textField);
			if (userRecover == null) {
				request.setAttribute("recoverRespCode", 0);
			} else {
				request.setAttribute("recoverRespCode", 1);
				String code = recoverUser(userRecover);
				request.getSession().setAttribute("userRecover", userRecover);
				request.getSession().setAttribute("codeRecover", code);
			}
		} else if ("level2".equals(process) && null != request.getSession().getAttribute("codeRecover")) {
			System.out.println("process " + process);
			String c = (String) request.getSession().getAttribute("codeRecover");
			if (c.equals(textField)) {
				request.getSession().setAttribute("codeRecover", null);
				request.getSession().setAttribute("recoverSuccess", true);
				request.setAttribute("recoverRespCode", 4);
			} else {
				request.setAttribute("recoverRespCode", 3);
			}
		} else if ("level3".equals(process) && null != request.getSession().getAttribute("recoverSuccess")) {
			if (bindingResult.hasFieldErrors("username") && bindingResult.hasFieldErrors("password")) {
				request.setAttribute("recoverRespCode", 4);
				return "recoverUser";
			} else {
				User userRecover = (User) request.getSession().getAttribute("userRecover");
				userRecover.setSalt(HashUtil.generateSalt(UUID.randomUUID().toString()));
				userRecover.setPassword(HashUtil.hashPassword(ruser.getPassword(), userRecover.getSalt()));
				UserDAO.Update(userRecover);
				return "redirect:";
			}
		}
		return "recoverUser";
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
		mail.run();
		return code;
	}

}
