/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facebook.api;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import com.facebook.entity.FBProfile;
import com.facebook.entity.UserProfile;
import com.google.gson.Gson;
import com.matrimony.util.IOUtils;

/**
 *
 * @author SON
 */
public class FBGraph {

	public FBProfile getFBProfile(String accessToken) {
		String jsonData = null;
		String g = "https://graph.facebook.com/me?" + accessToken;
		try {
			URL url = new URL(g);
			URLConnection connection = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuilder b = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				b.append(inputLine).append("\n");
			}
			in.close();
			jsonData = b.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson json = new Gson();
		System.out.println(jsonData);
		return json.fromJson(jsonData, FBProfile.class);
	}

	public UserProfile gatherUserProfile(String accessToken) throws MalformedURLException, IOException {
		UserProfile userProfile = null;
		StringBuilder link = new StringBuilder("https://graph.facebook.com/me?");
		link.append("fields=name,first_name,last_name,middle_name,age_range,email,link,birthday,locale");
		link.append("&access_token=");
		link.append(accessToken);
		HttpURLConnection conn = (HttpURLConnection) new URL(link.toString()).openConnection();
		String json = IOUtils.toString(conn.getInputStream());
		System.out.println(json);
		return userProfile;
	}

	public BufferedImage getFbGraphAvatar(String userId) {
		try {
			URL imgUrl = new URL("https://graph.facebook.com/" + userId + "/picture?type=large");
			System.out.println(imgUrl);
			return ImageIO.read(imgUrl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		FBGraph graph = new FBGraph();
		try {
			graph.gatherUserProfile("CAAOZCUTLuboQBAKwkzXJp3zOxXXpzTkrRUuZCcJyU9qRexQZAPs0ToyUM9i294fCSu6lZAFfq0BJ6ckOd0y8EFTdRN971Rty4pqZAXoBoLeHKBHTUuyvD64xI19Owg9BQG34V8UDv5xCaz6h5BicBRvgSnwKJ7ZAjoNeu8LoI6DBKtqY2qkj3XKHKmUDFx7S4ZD&expires=5183026");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
