package com.app.spring.service.model;

import com.app.spring.service.Constants;

public class ResponseBase {

   private String responseCode;
   private String responseMessage;
   private boolean success;
   
   public ResponseBase() {
      super();
    }
   
   public ResponseBase(String pResponseCode, String pResponseMessage) {
      this.responseCode = pResponseCode;
      this.responseMessage = pResponseMessage;
      this.success = Constants.RESPONSE_CODE_OK.equals(pResponseCode);
   }
   
   
   public String getResponseCode() {
      return responseCode;
   }


   public void setResponseCode(String responseCode) {
      this.responseCode = responseCode;
   }

   public String getResponseMessage() {
      return responseMessage;
   }

   public void setResponseMessage(String responseMessage) {
      this.responseMessage = responseMessage;
   }

   public boolean isSuccess() {
      return success;
   }

   public void setSuccess(boolean success) {
      this.success = success;
   }
   
   public void setResponseOk() {
      this.responseCode = Constants.RESPONSE_CODE_OK;
      this.responseMessage = Constants.RESPONSE_MESSAGE_OK;
      this.success = true;
   }
   
   public void setResponseError(String pResponseMessage) {
      this.responseCode = Constants.RESPONSE_CODE_ERROR;
      this.responseMessage = pResponseMessage;
      this.success = false;
   }
   
   public void setResponseNoAuth(String pResponseMessage) {
	   this.responseCode = Constants.RESPONSE_CODE_UNAUTHORIZED;
	   this.responseMessage = pResponseMessage;
	   this.success = false;
   }
}
