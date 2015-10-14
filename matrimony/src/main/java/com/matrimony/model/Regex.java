/**
 * 
 */
package com.matrimony.model;

/**
 * @author SON
 *
 */
public class Regex {
	public final static String NAME="^[\\p{L}a-z ,.'-]{2,30}$";
	public final static String GENDER=Convention.FEMALE+"|"+Convention.MALE;
	public final static String EMAIL="^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
	public final static String PHONE="[a-zA-Z0-9]{2,30}";
	public final static String PASSWORD="^[A-Za-z\\d]{8,}$";
	public final static String LOGIN_NAME="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
}
