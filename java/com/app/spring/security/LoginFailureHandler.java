package com.app.spring.security;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.app.spring.service.Constants;
import com.app.spring.service.model.ResponseBase;

public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler  {

	private Logger log;
	
	public LoginFailureHandler() {
		super();
		this.log = Logger.getLogger(this.getClass());
	}
	
    @Override
    public void onAuthenticationFailure(HttpServletRequest pRequest,
		   HttpServletResponse pResponse, AuthenticationException pAuthException) throws IOException, ServletException {
	 	 ObjectMapper mapper = new ObjectMapper();
		 ResponseBase responseBase = new ResponseBase();
		 this.getLog().info("Invalid Authentication; [Reason: " + pAuthException.getMessage() + "]");
		 responseBase.setResponseError(Constants.RESPONSE_MESSAGE_INVALID_USER);
		 pResponse.setContentType(Constants.CONTENT_TYPE_JSON);
		 OutputStream out = pResponse.getOutputStream();
		 mapper.writeValue(out, responseBase);
   	 }
   
   private Logger getLog() {
	   return this.log;
   }
}
