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

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public UsernameNotExist(String msg) {
            super(msg);
        }
    }

    public static class WrongPassword extends Exception {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public WrongPassword(String msg) {
            super(msg);
        }
    }
    public static class EmailAlready extends Exception {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public EmailAlready(String msg) {
            super(msg);
        }
    }
    public static class ContactNumberAlready extends Exception {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ContactNumberAlready(String msg) {
            super(msg);
        }
    }
     public static class UsernameAlready extends Exception {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public UsernameAlready(String msg) {
            super(msg);
        }
    }
     public static class EmptySuggest extends Exception {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public EmptySuggest(String msg) {
            super(msg);
        }
    }
     public static class EmptyRequest extends Exception {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public EmptyRequest(String msg) {
            super(msg);
        }
    }
     public static class EmptyFriend extends Exception {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public EmptyFriend(String msg) {
            super(msg);
            
        }
    }
}
