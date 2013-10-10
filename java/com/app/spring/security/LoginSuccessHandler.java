package com.app.spring.security;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.app.spring.service.Constants;
import com.app.spring.service.model.ResponseBase;

public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private String PAGE_REDIRECT_URL = "./home.jsp";
	private Logger log;
	
	public LoginSuccessHandler() {
		super();
		this.log = Logger.getLogger(this.getClass());
	}
	
   @Override
   public void onAuthenticationSuccess(HttpServletRequest pRequest,
		   HttpServletResponse pResponse, Authentication pAuthentication) throws IOException, ServletException {
	  ObjectMapper mapper = new ObjectMapper();
	  
	  ResponseBase responseBase = new ResponseBase();
	  responseBase.setSuccess(true);
	  responseBase.setResponseCode(Constants.RESPONSE_CODE_OK);
	  responseBase.setResponseMessage(PAGE_REDIRECT_URL);
	  
	  this.getLog().info("Login Successful; [Redirecting: " + PAGE_REDIRECT_URL + "]");
	  
	  pResponse.setContentType(Constants.CONTENT_TYPE_JSON);
	  OutputStream out = pResponse.getOutputStream();
	  mapper.writeValue(out, responseBase);      
   }
   
   private Logger getLog() {
	   return this.log;
   }
}
