package com.spring.socket.util;

public class SpringSecurity {
  public static final String LOGIN_URL = "/auth/login";
  public static final String LOGIN_PROCESS_URL = LOGIN_URL + "/process";
  public static final String LOGIN_SUCCESS_URL = "/";
  public static final String LOGOUT_URL = "/auth/logout";
  public static final String NO_ACCESS_PERMISSION_URL = "/auth/no-access-permission";
  public static final String PARAM_USERNAME = "username";
  public static final String PARAM_PASSWORD = "password";
}
