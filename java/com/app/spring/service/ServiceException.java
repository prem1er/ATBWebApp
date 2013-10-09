package com.app.spring.service;

public class ServiceException extends Exception {

   private static final long serialVersionUID = 1063208985955348454L;

   private String responseCode;
   
   public ServiceException(String pResponseCode, String pResponseMessage) {
      super(pResponseMessage);
      this.responseCode = pResponseCode;
   }
   
   public String getResponseCode() {
      return this.responseCode;
   }
}
