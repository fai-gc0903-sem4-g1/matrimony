/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matrimony.database.FriendDAO;
import com.matrimony.database.Matrimony;
import com.matrimony.entity.Friend;
import com.matrimony.entity.User;

/**
 *
 * @author anhdh_a05370
 */
@Controller
public class FriendController {

    @RequestMapping(value = "listSuggest", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String listSuggest(HttpSession ss) throws IOException {
        List<User> listSuggest = new ArrayList<User>();
        User u = (User) ss.getAttribute("user");
        List<User> list = Matrimony.getSuggestUsers(u);
        for (int i = 0; i < list.size(); i++) {
            if (!FriendDAO.CheckExist(u.getId(), list.get(i).getId())) {
                listSuggest.add(list.get(i));
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        String data = mapper.writeValueAsString(listSuggest);
        System.out.println(data);
        return data;
    }

    @RequestMapping(value = "allInvite", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String allInvite(HttpSession ss) throws IOException {
        User u = (User) ss.getAttribute("user");
        List<User> list = FriendDAO.ListInvite(u.getId());
        ObjectMapper mapper = new ObjectMapper();
        String data = mapper.writeValueAsString(list);
        System.out.println(data);
        return data;
    }

    @RequestMapping(value = "allRequest", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String allRequest(HttpSession ss) throws IOException {
        User u = (User) ss.getAttribute("user");
        System.out.println(u.getId());
        List<User> list = FriendDAO.ListRequest(u.getId());
        for (int i = 0; i < list.size(); i++) {
            list.get(i).getId();
        }
        ObjectMapper mapper = new ObjectMapper();
        String data = mapper.writeValueAsString(list);
        System.out.println(data);
        return data;
    }

    @RequestMapping(value = "allFriend", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String allFriend(HttpSession ss) throws IOException {
        User u = (User) ss.getAttribute("user");
        List<User> list = FriendDAO.ListFriend(u.getId());
        ObjectMapper mapper = new ObjectMapper();
        String data = mapper.writeValueAsString(list);
        System.out.println(data);
        return data;
    }

    @RequestMapping(value = "addFriend", method = RequestMethod.POST)
    @ResponseBody
    public static String addFriend(String user, HttpSession ss) {
        User u = (User) ss.getAttribute("user");
        int status = 1;
        Friend f = new Friend();
        f.setUserFromId(u.getId());
        f.setUserToId(user);
        f.setStatus(status);
        FriendDAO.addFriend(f);
        return "success";//mo database ktra lai xem
    }

    @RequestMapping(value = "removeFriend", method = RequestMethod.POST)
    @ResponseBody
    public static String removeFriend(String user, HttpSession ss) {
        String s="null";
        User u = (User) ss.getAttribute("user");
        List<Friend> f = FriendDAO.GetFriend(u.getId(), user);
        for (int i = 0; i < f.size(); i++) {
            FriendDAO.removeFriend(f.get(i));
            s="success";
        }
        return s;
    }

    @RequestMapping(value = "acceptFriend", method = RequestMethod.POST)
    @ResponseBody
    public static String acceptFriend(String user, HttpSession ss) {
        User u = (User) ss.getAttribute("user");
        System.out.println(user);
        FriendDAO.AcceptFriend(u.getId(), user);
        return "success";//mo database ktra lai xem
    }
}
