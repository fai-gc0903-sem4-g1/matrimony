/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
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
	public String doStringRecover(HttpServletRequest request, String textField, String process, String uCode, User recoverUser) {
		if ("level1".equals(process)) {
			System.out.println("process " + process);
			User temp = UserDAO.findByEmail(textField);
			if (temp == null) {
				request.setAttribute("recoverRespCode", 0);
			} else {
				request.setAttribute("recoverRespCode", 1);
				String code = recoverUser(temp);
				request.getSession().setAttribute("codeRecover", code);
			}
		} else if ("level2".equals(process) && null != request.getSession().getAttribute("codeRecover")) {
			System.out.println("process " + process);
			String c = (String) request.getSession().getAttribute("codeRecover");
			if (c.equals(uCode)) {
				request.getSession().setAttribute("codeRecover", null);
				request.getSession().setAttribute("recoverSuccess", true);
				request.setAttribute("recoverRespCode", 4);
			} else {
				request.setAttribute("recoverRespCode", 3);
			}
		}
		else if ("level3".equals(process) && null != request.getSession().getAttribute("recoverSuccess")) {
			System.out.println("process " + process);
			String c = (String) request.getSession().getAttribute("codeRecover");
			if (c.equals(uCode)) {
				request.getSession().setAttribute("codeRecover", null);
				request.getSession().setAttribute("recoverSuccess", true);
				request.setAttribute("recoverRespCode", 4);
			} else {
				request.setAttribute("recoverRespCode", 3);
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
		mail.send();
		return code;
	}

}
