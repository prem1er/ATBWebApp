package com.app.spring.service;

public interface Constants {

   public static final String RESPONSE_CODE_OK = "200";
   public static final String RESPONSE_CODE_UNAUTHORIZED = "401";
   public static final String RESPONSE_CODE_ERROR = "500";
   public static final String RESPONSE_CODE_CONFLICT = "409";
   public static final String RESPONSE_MESSAGE_OK = "OK";
   
   public static final String CONTENT_TYPE_JSON = "application/json";
   
   public static final String RESPONSE_MESSAGE_INVALID_USER = "Invalid User Name/Password";
   public static final String RESPONSE_MESSAGE_UNAUTHORIZED = "Unauthorized Access";
   public static final String RESPONSE_MESSAGE_PASSWORD_MATCH = "Passwords Don't Match";
   public static final String RESPONSE_MESSAGE_SYSTEM_ERROR = "System Error";
   public static final String RESPONSE_MESSAGE_USER_EXISTS = "User Name already exists.";
   public static final String RESPONSE_MESSAGE_VALID_USER = "Valid User Name";

   //DB Tables
   public static final String TABLE_USERS = "users";
   public static final String TABLE_USER_ROLES = "user_roles";
   
   //User DB Fields
   public static final String USER_FIELD_EMAIL = "EMAIL";
   public static final String USER_FIELD_FIRST_NAME = "FIRST_NAME";
   public static final String USER_FIELD_LAST_NAME = "LAST_NAME";
   public static final String USER_FIELD_USER_NAME = "USER_NAME";
   public static final String USER_FIELD_PASSWORD = "PASSWORD";
   public static final String USER_FIELD_USER_ID = "USER_ID";
   public static final String USER_FIELD_ENABLED = "ENABLED";
   public static final String USER_FIELD_NON_EXPIRED = "NON_EXPIRED";
   public static final String USER_FIELD_NON_LOCKED = "NON_LOCKED";
   public static final String USER_FIELD_NON_CRED_EXPIRED = "CREDENTIALS_NON_EXPIRED";
   
   //Authorities DB Fields
   public static final String USER_ROLE_FIELD_AUTH = "AUTHORITY";
   
   //Authorities DB Values
   public static final String USER_ROLE_VALUE_GENERAL = "ROLE_USER";
   public static final String USER_ROLE_VALUE_ADMIN = "ROLE_ADMIN";
   
   public static final int DB_VALUE_TRUE = 1;
   public static final int DB_VALUE_FALSE = 0;
}
