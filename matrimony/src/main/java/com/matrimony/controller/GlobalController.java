/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.controller;

import javax.websocket.WebSocketContainer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.matrimony.database.UserDAO;
import com.matrimony.entity.User;
import javax.websocket.*;
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
			return "404";
		}
	}
	
	@RequestMapping(value = "chat", method = RequestMethod.GET)
	public String viewChat() {
		return "chatSnippet";
	}
}
