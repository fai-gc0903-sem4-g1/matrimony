/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author SON
 */
@Controller
public class GlobalController {
    @RequestMapping(value="/")
    public String index(HttpSession session){
        System.out.println("Go to index OK");
        return "index";
    }
    
    
}
