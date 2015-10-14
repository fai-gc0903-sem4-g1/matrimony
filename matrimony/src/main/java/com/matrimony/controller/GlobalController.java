/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.matrimony.database.UserDAO;
import com.matrimony.entity.User;

/**
 *
 * @author SON
 */
@Controller
public class GlobalController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewIndex(HttpServletRequest request) {
		User ssUser = (User) request.getSession().getAttribute("user");
//		boolean hasExpiries = !UserDAO.hasExpiries(ssUser);
//		System.out.println("user: "+ssUser);

		if (ssUser == null)
			return "joinUs";
		else if (!UserDAO.hasExpiries(ssUser)){
			System.out.println("Nguoi dung con han: "+UserDAO.hasExpiries(ssUser));
			return "redirect:payment";
		}
		else
			return "home";
	}

	@RequestMapping(value = "{username}")
	public String doProfile(@PathVariable("username") String username, HttpServletRequest request) {
		System.out.println(username);
		User user = UserDAO.findByUsername(username);
		if (user != null) {
			System.out.println(user.isVerified());
		}
		if (user != null && user.isVerified()) {
			request.setAttribute("userGuest", user);
			return "profile";
		} else {
			return "404";
		}
	}
}
