/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author SON
 */
public class HashUtil {
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    
    public static String hashPassword(String password, String salt) {
        String result=null;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] passowrdHashed = md.digest(password.getBytes());
            result= bytesToHex(passowrdHashed);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HashUtil.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Algorithm not found");
        }
        return result;
    }
    
    public static String generateSalt(String stone){
        String result=null;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-1");
            byte[] passowrdHashed = md.digest(stone.getBytes());
            result= bytesToHex(passowrdHashed);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HashUtil.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Algorithm not found");
        }
        return result;
    }

    protected static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static void main(String[] args) {
        generateSalt("1234");
    }
}
