package com.app.spring.service.model;

public class ResponseUserData extends ResponseBase {

   private UserData userData;

   /**
    * @return userData
    */
   public UserData getUser() {
	   return userData;
   }

   /**
    * @param userData
    */
   public void setUser(UserData pUserData) {
	   this.userData = pUserData;
   }
}
