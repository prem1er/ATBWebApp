package com.app.spring.service.dao;

import com.app.spring.service.ServiceException;
import com.app.spring.service.model.UserData;

public interface UserServiceDAO extends ServiceBaseDAO {

	public UserData getUserInfo(String pUserId) throws ServiceException;
	
	public void createUser(UserData pUserData) throws ServiceException;
	
	public int verifyUserName(String pUserName) throws ServiceException;
}