/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.matrimony.database.UserDAO;
import com.matrimony.database.UserPreferenceDAO;
import com.matrimony.entity.User;
import com.matrimony.entity.UserPreference;
import com.matrimony.model.Regex;
import com.matrimony.model.SessionKey;
import com.matrimony.model.StringResouces;
import com.matrimony.model.UploadImageToServer;
import com.matrimony.util.Global;
import com.websocket.ShortUserProfile;

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

	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String updateProfile(HttpServletRequest request) {
		if (request.getSession().getAttribute("user") == null)
			return "joinUs";
		return "updateProfile";
	}

	@RequestMapping(value = "updateBasicProfile", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String updateBasicProfile(HttpServletRequest request, HttpServletResponse response, User userBasic) {
		User ssUser = (User) request.getSession().getAttribute("user");
		if (ssUser == null)
			return null;
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		Properties props = new Properties();
		boolean wellForm = true;
		System.out.println(userBasic.getEmail());
		StringResouces sr = new StringResouces(StringResouces.vi_VN);
		if (!Pattern.matches(Regex.NAME, userBasic.getFirstName())) {
			wellForm = false;
			props.put("txtFirstName", sr.getData().get("firstNameInvalid"));
		}
		if (!Pattern.matches(Regex.NAME, userBasic.getLastName())) {
			wellForm = false;
			props.put("txtLastName", sr.getData().get("lastNameInvalid"));
		}
		if (!Pattern.matches(Regex.EMAIL, userBasic.getEmail())) {
			wellForm = false;
			props.put("txtEmail", sr.getData().get("emailInvalid"));
		}
		if (!Pattern.matches(Regex.PHONE, userBasic.getContactNumber())) {
			wellForm = false;
			props.put("txtPhone", sr.getData().get("phoneInvalid"));
		}
		if (!"12345678".equals(userBasic.getPassword())) {
			if (!Pattern.matches(Regex.PASSWORD, userBasic.getPassword())) {
				wellForm = false;
				props.put("txtPassword", sr.getData().get("passwordInvalid"));
			}
		}
		if (wellForm) {
			ssUser.setUsername(userBasic.getUsername());
			ssUser.setLastName(userBasic.getLastName());
			ssUser.setFirstName(userBasic.getFirstName());
			ssUser.setMiddleName(userBasic.getMiddleName());
			ssUser.setName(ssUser.getLastName() + " " + ssUser.getMiddleName() + " " + ssUser.getFirstName());
			ssUser.setEmail(userBasic.getEmail());
			System.out.println(userBasic.getPassword());
			if (!"12345678".equals(userBasic.getPassword())) {
				ssUser.setPassword(userBasic.getPassword());
				ssUser.setSalt(null);
			}
			ssUser.setUpdateTime(currentTime);
			UserDAO.Update(ssUser);
		}
		props.put("wellForm", wellForm);
		String json = Global.gson.toJson(props);
		System.out.println(json);
		return json;
	}

	@RequestMapping(value = "updateMyMeasurements", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String doUpdateMyMeasurements(HttpServletRequest request, User userMeasurement) {
		User ssUser = (User) request.getSession().getAttribute("user");
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		if (ssUser == null)
			return null;
		Properties props = new Properties();
		props.put("wellForm", true);
		ssUser.setHeight(userMeasurement.getHeight());
		ssUser.setWeight(userMeasurement.getWeight());
		ssUser.setUpdateTime(currentTime);
		UserDAO.Update(ssUser);
		String json = Global.gson.toJson(props);
		return json;
	}

	@RequestMapping(value = "updateLikeMeasurements", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String doUpdateMeasurements(HttpServletRequest request, String gender, int ageFrom, int ageTo,
			int heightFrom, int heightTo, int weightFrom, int weightTo, String maritalStatus, String religion,
			String country, String city) {
		User ssUser = (User) request.getSession().getAttribute("user");
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		if (ssUser == null)
			return null;
		UserPreference uPreference = ssUser.getUserPreference();
		uPreference.setGender(gender);
		uPreference.setAgeGap(ageFrom+"-"+ageTo);
		uPreference.setHeightGap(heightFrom+"-"+heightTo);
		uPreference.setWeightGap(weightFrom+"-"+weightTo);
		uPreference.setMaritalStatus(maritalStatus);
		uPreference.setReligion(religion);
		uPreference.setCountryside(country);
		uPreference.setHometown(city);
		uPreference.setUpdatedTime(currentTime);
		UserPreferenceDAO.update(uPreference);
		Properties props = new Properties();
		props.put("wellForm", true);
		// uPreference.setAgeGap(ageGap);
		String json = Global.gson.toJson(props);
		return json;
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
						System.out.println("avatar folder: " + avatarFolderPath);

						// MAKE OBF NAME AVATAR IMAGE
						String obfName = RandomStringUtils.randomAlphanumeric(26);
						StringBuilder filePath = new StringBuilder(avatarFolderPath);
						filePath.append("/");
						filePath.append(obfName);
						filePath.append(".");
						filePath.append(extensionFile[1]);

						UploadImageToServer upload = new UploadImageToServer();
						upload.upload(filePath.toString(), p.getInputStream());
						System.out.println("Uploaded " + filePath.toString());
						// UPDATE AVATAR
						UserDAO.Update(ssUser);
						ssUser.setAvatarPhoto(obfName + "." + extensionFile[1]);
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

	@RequestMapping(value = "taotest", method = RequestMethod.GET)
	public String taotest(HttpServletRequest request, HttpServletResponse response, String name) {
		System.out.println("TEST " + name);
		return "redirect:";
	}

	@RequestMapping(value = "shortUserProfile", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getInfoUserByUserId(HttpServletRequest request, HttpServletResponse response, String id) {
		System.out.println("Tim id: " + id);
		try {
			request.setCharacterEncoding("UTF8");
			response.setCharacterEncoding("UTF8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		User user = UserDAO.findById(id);
		ShortUserProfile sortUserProfile = new ShortUserProfile();
		sortUserProfile.setAvatar(user.getAvatarPhoto());
		sortUserProfile.setName(user.getName());
		sortUserProfile.setUsername(user.getUsername());
		String json = Global.gson.toJson(sortUserProfile);
		return json;
	}
}
