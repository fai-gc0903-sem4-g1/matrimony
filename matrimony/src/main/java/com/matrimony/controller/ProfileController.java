/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.matrimony.database.UserDAO;
import com.matrimony.entity.User;
import com.matrimony.model.SessionKey;
import com.matrimony.model.UploadImageToServer;
import com.matrimony.util.Global;
import com.websocket.SortUserProfile;

/**
 *
 * @author LaptopF5
 */
@Controller
public class ProfileController {
	@RequestMapping(value = "profile", method = RequestMethod.GET)
	public String infor() {
		return "profile";
	}
	
	@RequestMapping(value = "sontest", method = RequestMethod.GET)
	public String sontest() {
		return "sontest";
	}

	@RequestMapping(value = "changeAvatar", method = RequestMethod.POST)
	public String doChangeAvatar(HttpServletRequest request) {
		User ssUser = (User) request.getSession().getAttribute(SessionKey.USER);
		if (ssUser == null)
			return "joinUs";
		try {
			Collection<Part> parts = request.getParts();
			for (Part p : parts) {
				String originName = p.getSubmittedFileName();
				if (originName != null) {
					System.out.println(originName);
					String[] extensionFile = originName.split("\\.");
					if (extensionFile.length > 1) {
						String avatarFolderPath = request.getServletContext().getRealPath("/resources/profile/avatar");
						System.out.println(avatarFolderPath);
						String obfName = RandomStringUtils.randomAlphanumeric(26);
						StringBuilder filePath = new StringBuilder(avatarFolderPath);
						filePath.append(obfName);
						filePath.append(".");
						filePath.append(extensionFile[1]);
						UploadImageToServer upload = new UploadImageToServer();
						upload.upload(filePath.toString(), p.getInputStream());
						System.out.println("Uploaded " + filePath.toString());
						// UPDATE AVATAR
						ssUser.setAvatarPhoto(obfName+"."+extensionFile[1]);
						UserDAO.Update(ssUser);
						return "redirect:";
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "profile";
	}
	
	
	
	@RequestMapping(value="sortUserProfile", method=RequestMethod.POST)
	@ResponseBody
	public String getInfoUserByUserId(String id){
		User user=UserDAO.findById(id);
		SortUserProfile sortUserProfile=new SortUserProfile();
		sortUserProfile.setAvatar(user.getAvatarPhoto());
		sortUserProfile.setName(user.getName());
		sortUserProfile.setUsername(user.getUsername());
		String json=Global.gson.toJson(sortUserProfile);
		return json;
	}
}
