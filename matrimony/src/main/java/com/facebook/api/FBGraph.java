/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facebook.api;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.facebook.entity.UserProfile;
import com.matrimony.util.Global;
import com.matrimony.util.IOUtils;

/**
 *
 * @author SON
 */
public class FBGraph {
	public static UserProfile gatherUserProfile(String accessToken) throws MalformedURLException, IOException {
		UserProfile userProfile = null;
		StringBuilder link = new StringBuilder("https://graph.facebook.com/me?");
		link.append("fields=name,first_name,last_name,middle_name,age_range,email,link,birthday,locale");
		link.append("&access_token=");
		link.append(accessToken);
		HttpURLConnection conn = (HttpURLConnection) new URL(link.toString()).openConnection();
		String json = IOUtils.toString(conn.getInputStream());
		System.out.println(json);
		userProfile=Global.gson.fromJson(json, UserProfile.class);
		return userProfile;
	}

	public static InputStream gatherAvatarImage(String facebookId) throws IOException{
		StringBuilder link=new StringBuilder("https://graph.facebook.com/");
		link.append(facebookId);
		link.append("/picture?type=large");
		HttpURLConnection conn=(HttpURLConnection) new URL(link.toString()).openConnection();
		return conn.getInputStream();
	}

	public static void main(String[] args) {
		
	}
}
