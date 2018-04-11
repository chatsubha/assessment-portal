package com.portal.util;

public class LoginConstant {

	public static final String USER_ROLE_SQL = "SELECT USER_ROLE FROM AP.TBLUSERS WHERE USER_NAME = ? ";
	public static final String USER_PASSWORD_SQL = "SELECT PASSWORD FROM AP.TBLUSERS WHERE USER_NAME = ?";
	public static final String USER_ID_SQL = "SELECT USER_ID FROM AP.TBLUSERS WHERE USER_NAME = ?";
}
