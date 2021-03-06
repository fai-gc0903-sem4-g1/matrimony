/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facebook.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 *
 * @author SON
 */
public class FBConnection {
    public static final String FB_APP_ID = "1054780397874820";
    public static final String FB_APP_SECRET = "7f3633387fcb4d112913233a5efb19bb";
    public static final String REDIRECT_URI = "http://localhost/matrimony/fbredirect";

    public String getFBAuthUrl() {
        String fbLoginUrl = "";
        try {
            fbLoginUrl = "http://www.facebook.com/dialog/oauth?" + "client_id=" + FB_APP_ID + "&redirect_uri=" + URLEncoder.encode(REDIRECT_URI, "utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fbLoginUrl;
    }
    
    private String makeGraphUrl(String code) {
        String fbGraphUrl = "";
        try { 
            fbGraphUrl = "https://graph.facebook.com/oauth/access_token?"
                    + "client_id=" + FB_APP_ID
                    + "&redirect_uri=" + URLEncoder.encode(REDIRECT_URI, "utf-8")
                    + "&client_secret=" + FB_APP_SECRET
                    + "&code=" + code;
            		System.out.println(fbGraphUrl);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fbGraphUrl;
    }

    public String getAccessToken(String code) throws MalformedURLException, IOException {
        String accessToken;
        URL fbGraphUrl;
        fbGraphUrl = new URL(makeGraphUrl(code));
        URLConnection fbConnection;
        StringBuilder b = new StringBuilder();
        fbConnection = fbGraphUrl.openConnection();
        BufferedReader br=new BufferedReader(new InputStreamReader(fbConnection.getInputStream()));
        String inputLine;
        while ((inputLine = br.readLine()) != null) {
            b.append(inputLine).append("\n");
        }
        br.close();
        accessToken = b.toString();
        if (accessToken.startsWith("{")) {
            throw new RuntimeException("ERROR: Access token invalid: " + accessToken);
        }
        return accessToken.split("access_token")[1];
    }
    
    public static void main(String[] args) {
	}
}
