/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.controller;

import com.matrimony.database.FriendDAO;
import com.matrimony.entity.TableFriends;
import com.matrimony.entity.User;
import com.matrimony.exception.STException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author anhdh_a05370
 */
public class FriendController {

    @RequestMapping(value = "askFriend", method = RequestMethod.POST)
    public String sendRequest(String nameToId, String nameFromId, TableFriends table, ModelMap mm, HttpServletRequest request, HttpSession session) throws STException.EmptySuggest, STException.EmptyRequest {
        User friendFromId = FriendDAO.getUserById(nameFromId);
        table.setFriendFromId(nameFromId);
        table.setFriendToId(nameToId);
        table.setStatus(1);
        FriendDAO.addTableFriends(table);//them moi 1 bang ket ban voi thuoc tinh da gui loi moi
        FriendController f = new FriendController();
        f.getFriend(friendFromId, mm, request);
        session.setAttribute("nameToId", nameToId);
        session.setAttribute("message", "Da gui loi moi ket ban den"+nameToId);
        return "home";
    }

    @RequestMapping(value = "acceptRequest/{nameToId}/{nameFromId}", method = RequestMethod.POST)
    public String acceptRequest(@PathVariable String nameToId, ModelMap mm, HttpServletRequest request, HttpSession session, @PathVariable String nameFromId, TableFriends table) {
        FriendDAO.EditRecord(nameFromId, nameToId, 2);//sua doi thuoc tinh status thanh da dong y ket ban
        User friendFromId = FriendDAO.getUserById(nameFromId);
        FriendController f = new FriendController();
        f.getFriend(friendFromId, mm, request);
        session.setAttribute("nameToId", nameToId);
        session.setAttribute("message", nameFromId + nameToId + "Da tro thanh ban be");
        return "home";
    }
    
    @RequestMapping(value = "cancelRequest/{nameToId}/{nameFromId}", method = RequestMethod.POST)
    public String cancelRequest(@PathVariable String nameToId, ModelMap mm, HttpServletRequest request, HttpSession session, @PathVariable String nameFromId, TableFriends table) {
        return "home";
    }
    
    @RequestMapping(value = "removeFriend/{nameToId}/{nameFromId}", method = RequestMethod.POST)
    public String removeFriend(@PathVariable String nameToId, ModelMap mm, HttpServletRequest request, HttpSession session, @PathVariable String nameFromId, TableFriends table) {
        return "home";
    }

    public void getFriend(User user, ModelMap mm, HttpServletRequest request) {
        List<User> listSuggest = null;
        List<User> listRequest = null;
        List<User> listFriend = null;
        try {
            listSuggest = FriendDAO.searchBySttToGetSuggest(user);//lay danh sach nhung loi goi y
            listRequest = FriendDAO.searchBySttToGetRequest(user);//lay danh sach nhung nguoi da gui loi moi
            listFriend = FriendDAO.searchBySttToGetFriend(user);//lay danh sach la ban be
            mm.addAttribute("listSuggest", listSuggest);
            mm.addAttribute("listRequest", listRequest);
            mm.addAttribute("listFriend", listFriend);
        } catch (STException.EmptySuggest ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("notice", "khong co goi y ket ban");
        } catch (STException.EmptyRequest ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("notice", "khong co loi moi ket ban");
        } catch (STException.EmptyFriend ex) {
            Logger.getLogger(FriendController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("notice", "khong co ban be");
        }
    }
}
