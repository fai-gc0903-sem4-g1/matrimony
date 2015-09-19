/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.controller;

import com.matrimony.database.FriendDAO;
import com.matrimony.database.UserDAO;
import com.matrimony.entity.Friend;
import com.matrimony.entity.User;
import com.matrimony.exception.STException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author anhdh_a05370
 */
@Controller
public class FriendController {

    @RequestMapping(value = "sendRequest", method = RequestMethod.POST)
    public String sendRequest(String usToId, String usFromId, HttpSession session) {
        Friend friend = new Friend();
        friend.setUserFromId(usFromId);
        friend.setUserToId(usToId);
        friend.setStatus(1);
        FriendDAO.addFriend(friend);//them moi 1 bang ket ban voi thuoc tinh da gui loi moi
        return "home";

    }

    @RequestMapping(value = "acceptRequest", method = RequestMethod.POST)
    public String accpetRequest(String usToId, String usFromId, HttpSession session) {
        Friend f = FriendDAO.getFriend(usFromId, usToId);
        f.setStatus(2);
        FriendDAO.EditRecord(f);
        return "home";
        //sua doi thuoc tinh status thanh da dong y ket ban
//        User friendFromId = FriendDAO.getUserById(nameFromId);
//        FriendController f = new FriendController();
//        f.getFriend(friendFromId, mm, request);
//        session.setAttribute("nameToId", nameToId);
//        session.setAttribute("message", nameFromId + nameToId + "Da tro thanh ban be");     
    }

    @RequestMapping(value = "cancelRequest", method = RequestMethod.POST)
    public String cancelRequest(String usTo, String usFrom) {
        return "home";
    }
//
//    @RequestMapping(value = "removeFriend/{nameToId}/{nameFromId}", method = RequestMethod.POST)
//    public String removeFriend(@PathVariable String nameToId, ModelMap mm, HttpServletRequest request, HttpSession session, @PathVariable String nameFromId, Friend table) {
//        return "home";
//    }
//    public void getFriend(User user, ModelMap mm, HttpServletRequest request) {
//        List<User> listSuggest = null;
//        List<User> listRequest = null;
//        List<User> listFriend = null;
//        try {
//            listSuggest = FriendDAO.searchBySttToGetSuggest(user);//lay danh sach nhung loi goi y
//            listRequest = FriendDAO.searchBySttToGetRequest(user);//lay danh sach nhung nguoi da gui loi moi
//            listFriend = FriendDAO.searchBySttToGetFriend(user);//lay danh sach la ban be
//            mm.addAttribute("listSuggest", listSuggest);
//            mm.addAttribute("listRequest", listRequest);
//            mm.addAttribute("listFriend", listFriend);
//        } catch (STException.EmptySuggest ex) {
//            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
//            request.setAttribute("notice", "khong co goi y ket ban");
//        } catch (STException.EmptyRequest ex) {
//            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
//            request.setAttribute("notice", "khong co loi moi ket ban");
//        } catch (STException.EmptyFriend ex) {
//            Logger.getLogger(FriendController.class.getName()).log(Level.SEVERE, null, ex);
//            request.setAttribute("notice", "khong co ban be");
//        }
//    }
}
