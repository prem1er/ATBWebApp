package com.app.spring.service.rest;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.app.spring.service.Constants;
import com.app.spring.service.ServiceException;
import com.app.spring.service.dao.UserServiceDAO;
import com.app.spring.service.model.ResponseBase;
import com.app.spring.service.model.ResponseUserData;
import com.app.spring.service.model.UserData;
import com.sun.xml.bind.v2.runtime.reflect.opt.Const;

public class UserServiceImpl implements UserService {
   
	private UserServiceDAO userServiceDAO;
	
	private Logger log;
	
	public UserServiceImpl() {
		this.log = Logger.getLogger(this.getClass());
	}

    @Override
	public ResponseUserData getUserInfo(String pUserId) {
	    getLog().info("Calling service: getUserInfo; [User ID: " + pUserId + "]");
	   
	   	ResponseUserData response = new ResponseUserData();
	   	UserData user = null;
		try {
			user = this.getUserServiceDAO().getUserInfo(pUserId);
			getLog().info("End call: getUserInfo");
			
			response.setUser(user);
			response.setResponseOk();
		} catch(ServiceException anEx) {
			response.setResponseError(anEx.getMessage());
		}
		
		return response;
	}

	@Override
	public ResponseBase createUser(String pUserName, String pPassword, String pEmail, 
			String pFirstName, String pLastName) {
		getLog().info("Calling service: createUser; [User Name: " + pUserName + "; Password: " + pPassword + "; Email: " + pEmail +
			"; First Name: " + pFirstName + "; Last Name: " + pLastName + "]");
		
		ResponseBase response = new ResponseBase();
		
		UserData newUser = new UserData(pUserName, pEmail, pFirstName, pLastName);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String hashedPassword = encoder.encode(pPassword);
		
		newUser.setHashedPassword(hashedPassword);
		
		try {
			this.userServiceDAO.createUser(newUser);
			getLog().info("End call: createUser");
			
			response.setResponseCode(Constants.RESPONSE_CODE_OK);
			response.setResponseMessage("User created successfully. Follow the instruction in the email to activate.");
		} catch(ServiceException anEx) {
			response.setResponseError(anEx.getMessage());
		}
		return response;
	}

	@Override
	public ResponseBase verifyUserName(String pUserName) {
		getLog().info("Calling service: verifyUserName; [User Name: " + pUserName + "]");
		
		ResponseBase response = new ResponseBase();
		try {
			int userId = this.userServiceDAO.verifyUserName(pUserName);
			getLog().info("End call: verifyUser");
			
			if (userId > 0) {
				response.setResponseCode(Constants.RESPONSE_CODE_CONFLICT);
				response.setResponseMessage(Constants.RESPONSE_MESSAGE_USER_EXISTS);
			} else if (userId == 0) {
				response.setResponseOk();
			}
			
		} catch (ServiceException anEx) {
			getLog().error(anEx.getMessage());
			response.setResponseError(anEx.getMessage());
		}
		return response;
	}
	
	@Override
	public ResponseBase getValidAccounts(String pFirstName, String pLastName,
         String pEmail, String pUserName, String pOrigPass, String pVerifyPass) {
      ResponseBase response = new ResponseBase();
      try {
         if(!pOrigPass.equals(pVerifyPass)) {
            throw new ServiceException(Constants.RESPONSE_CODE_ERROR, Constants.RESPONSE_MESSAGE_PASSWORD_MATCH);
         }
         
         //String encryptedPass = this.getUserServiceDAO().getEncryptedPassword(pOrigPass);
        //this.getUserServiceDAO().createUser(pFirstName, pLastName, pEmail, pUserName, encryptedPass);
         
         response.setResponseOk();
         
      } catch(ServiceException anEx) {
         
      }
      return response;
   }
	
    public UserServiceDAO getUserServiceDAO() {
		return this.userServiceDAO;
	}

	public void setUserServiceDAO(UserServiceDAO pUserServiceDAO) {
		this.userServiceDAO = pUserServiceDAO;
	}
	
	private Logger getLog() {
		return this.log;
	}

}
