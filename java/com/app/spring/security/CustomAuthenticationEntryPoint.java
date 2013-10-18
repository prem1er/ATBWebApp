package com.app.spring.security;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.app.spring.service.Constants;
import com.app.spring.service.model.ResponseBase;

public class CustomAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

	private static final String AJAX_REQUEST_HEADER = "XMLHttpRequest";
	private static final String REQUEST_HEAD_FIELD = "X-Requested-With";
	
	private Logger log;
	
	public CustomAuthenticationEntryPoint() {
		super();
		this.log = Logger.getLogger(this.getClass());
	}
	
	public void commence(HttpServletRequest pRequest, HttpServletResponse pResponse,
			AuthenticationException pAuthException) throws IOException, ServletException {
		
		if(AJAX_REQUEST_HEADER.equals(pRequest.getHeader(REQUEST_HEAD_FIELD))){
			ObjectMapper mapper = new ObjectMapper();
			ResponseBase responseBase = new ResponseBase();
			responseBase.setResponseNoAuth(Constants.RESPONSE_MESSAGE_UNAUTHORIZED);
			pResponse.setContentType(Constants.CONTENT_TYPE_JSON);
			OutputStream out = pResponse.getOutputStream();
			mapper.writeValue(out, responseBase);
		} else {
			getLog().info("Non-Authenticated Requested; [Requesting IP: " + pRequest.getRemoteAddr() + "]");
			super.commence(pRequest, pResponse, pAuthException);
		}
	}
	
	private Logger getLog() {
		return this.log;
	}
}
