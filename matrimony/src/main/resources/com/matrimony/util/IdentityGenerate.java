/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.util;

import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author SON
 */
public class IdentityGenerate {
    public static String genUsername(){
        String id="";
        Random rd=new Random();
        StringBuilder sb=new StringBuilder();
        sb.append(rd.nextInt(9)+1);
        for(int i=0;i<14;i++){
            sb.append(rd.nextInt(10));
        }
        System.out.println(sb.toString());
        return id;
    }
    public static void main(String[] args) {
        System.out.println(genUsername());
    }
}
