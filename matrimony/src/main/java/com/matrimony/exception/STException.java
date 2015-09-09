/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.exception;

/**
 *
 * @author SON
 */
public class STException {

    public static class UsernameNotExist extends Exception {

        public UsernameNotExist(String msg) {
            super(msg);
        }
    }

    public static class WrongPassword extends Exception {

        public WrongPassword(String msg) {
            super(msg);
        }
    }
    public static class EmailAlready extends Exception {

        public EmailAlready(String msg) {
            super(msg);
        }
    }
    public static class ContactNumberAlready extends Exception {
        public ContactNumberAlready(String msg) {
            super(msg);
        }
    }
     public static class UsernameAlready extends Exception {
        public UsernameAlready(String msg) {
            super(msg);
        }
    }
     public static class EmptySuggest extends Exception {
        public EmptySuggest(String msg) {
            super(msg);
        }
    }
     public static class EmptyRequest extends Exception {
        public EmptyRequest(String msg) {
            super(msg);
        }
    }
     public static class EmptyFriend extends Exception {
        public EmptyFriend(String msg) {
            super(msg);
        }
    }
}
