/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facebook.api;

import com.google.gson.Gson;

import facebook.entity.FBProfile;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

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
        FBGraph fbGraph=new FBGraph();
        BufferedImage img=fbGraph.getFbGraphAvatar("100002868986443");
       
        
    }
}
