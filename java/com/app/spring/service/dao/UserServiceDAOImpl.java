package com.app.spring.service.dao;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.app.spring.dbmap.UserActivationDataRowMapper;
import com.app.spring.dbmap.UserDataRowMapper;
import com.app.spring.service.Constants;
import com.app.spring.service.ServiceException;
import com.app.spring.service.model.UserData;
import com.app.spring.util.UserEmailUtil;

public class UserServiceDAOImpl extends ServiceBaseDAOImpl implements UserServiceDAO {

	private String userSchema;
	private String userRolesSchema;
	
	private UserEmailUtil userEmailUtil;
	
	private Logger log;

	public UserServiceDAOImpl() {
		this.log = Logger.getLogger(this.getClass());
	}
	
	public void setUserSchema(String pUserSchema) {
		this.userSchema = pUserSchema;
	}
	
	public void setUserRolesSchema(String pUserRolesSchema) {
		this.userRolesSchema = pUserRolesSchema;
	}

	public void setUserEmailUtil(UserEmailUtil pUserEmailUtil) {
		this.userEmailUtil = pUserEmailUtil;
	}
	
	public void setDataSource(DataSource pDataSource) {
		super.setDataSource(pDataSource);
	}
	
	@Override
	public String validateEmail(String pEmail) throws ServiceException {
		String email = "";
		
		try {
			email = this.getEmail(pEmail);
		} catch (EmptyResultDataAccessException anEx) {
			return email;
		} catch (Exception anEx) {
			throw new ServiceException(Constants.RESPONSE_CODE_ERROR, Constants.RESPONSE_MESSAGE_SUBMIT_ERROR);
		}
		
		return email;
	}

	@Override
	public int validateUserName(String pUserName) throws ServiceException {
		int userId = 0;
		try {
			userId = this.getUserId(pUserName);
		} catch(EmptyResultDataAccessException anEx) {
			return userId;
		} catch (Exception anEx){
			throw new ServiceException(Constants.RESPONSE_CODE_ERROR, Constants.RESPONSE_MESSAGE_SYSTEM_ERROR);
		}
		return userId;
	}

	@Override
	public void createUser(UserData pUserData) throws ServiceException {
		try {
			this.getJdbcTemplate().update(this.getSQLInsertNewUser(), new Object[] {pUserData.getUserName(), pUserData.getHashedPassword(),
				 	pUserData.getHashedActivationId(), pUserData.getFirstName(), pUserData.getLastName(), pUserData.getEmail(), 
				 	Constants.DB_VALUE_FALSE, Constants.DB_VALUE_TRUE, Constants.DB_VALUE_TRUE, Constants.DB_VALUE_TRUE});
			
			int userId = this.getUserId(pUserData.getUserName());
			
			if(userId > 0) {
				this.getJdbcTemplate().update(this.getSQLInsertUserAuthority(), new Object[] {userId, Constants.USER_ROLE_VALUE_GENERAL});
				this.userEmailUtil.sendActivateUserEmail(pUserData.getEmail(), userId, pUserData.getUserName(), pUserData.getHashedActivationId());
			} 
			
		} catch (DuplicateKeyException anEx) {
			getLog().error(anEx.getMessage());
			throw new ServiceException(Constants.RESPONSE_CODE_ERROR, Constants.RESPONSE_MESSAGE_SUBMIT_ERROR);
		} catch (Exception anEx) {
			getLog().error(anEx.getMessage());
			throw new ServiceException(Constants.RESPONSE_CODE_ERROR, Constants.RESPONSE_MESSAGE_SYSTEM_ERROR);
		}
	}
	
	@Override
	public UserData getUserInfo(String pUserId) throws ServiceException {
		UserData user = null;
		
		try {
			user = getJdbcTemplate().queryForObject(this.getSQLSelectUser(), new UserDataRowMapper(), pUserId);
		} catch (EmptyResultDataAccessException anEx) {
			getLog().equals("Invalid User ID .. " + anEx.getMessage());
			throw new ServiceException(Constants.RESPONSE_CODE_ERROR, Constants.RESPONSE_MESSAGE_SYSTEM_ERROR);
		} catch (Exception anEx) {
			getLog().error(anEx.getMessage());
			throw new ServiceException(Constants.RESPONSE_CODE_ERROR, anEx.getMessage());
		}
		
		return user;
	}

	@Override
	public void activateUser(String pUserId, String pActivationId) throws ServiceException {
		UserData user = null;
		
		try {
			user = (UserData) this.getJdbcTemplate().queryForObject(this.getSQLSelectUser(), new UserActivationDataRowMapper(), pUserId);	
			
			if(!user.isUserEnabled()) {
				if(pActivationId.equals(user.getHashedActivationId())) {
					this.getJdbcTemplate().update(this.getSQLUpdateActivateUser(), new Object[] {user.getUserId(), Constants.DB_VALUE_TRUE});
				} else {
					throw new ServiceException(Constants.RESPONSE_CODE_UNAUTHORIZED, "TODO: Attempt to register user without propert activation ID.");
				}
			} else {
				throw new ServiceException(Constants.RESPONSE_CODE_UNAUTHORIZED, "TODO: User is already activated. Send to login page");
			}
			
			
		} catch (EmptyResultDataAccessException anEx) {
			getLog().equals("Invalid User ID .. " + anEx.getMessage());
			throw new ServiceException(Constants.RESPONSE_CODE_UNAUTHORIZED, "TODO: Attempt to register a user id that doesn't exist.");
		} catch (Exception anEx) {
			getLog().error(anEx.getMessage());
			throw new ServiceException(Constants.RESPONSE_CODE_ERROR, Constants.RESPONSE_MESSAGE_SYSTEM_ERROR);
		}
	}

	private int getUserId(String pUserName) throws Exception {
		return this.getJdbcTemplate().queryForInt(this.getSQLSelectUserId(), new Object[]{pUserName});
	}
		
	private String getEmail(String pEmail) throws Exception {
		return (String) this.getJdbcTemplate().queryForObject(this.getSQLSelectEmail(), new Object[]{pEmail}, String.class);
	}
	
	public Logger getLog() {
		return this.log;
	}
	
	public String getSQLUpdateActivateUser() {
		return "update " + this.userSchema + 
			" set " + Constants.USER_FIELD_ENABLED + " = " + Constants.DB_VALUE_TRUE +
			" where " + Constants.USER_FIELD_USER_ID + " =  ?";
	}
	
	public String getSQLSelectEmail() {
		return "select " + Constants.USER_FIELD_EMAIL +
			" from " + this.userSchema + " where " + Constants.USER_FIELD_EMAIL + " = ?";  
	}
	
	public String getSQLSelectUser() {
		return "select " + Constants.USER_FIELD_ACTIVATION_ID + ", " +
			Constants.USER_FIELD_USER_NAME + ", " +
			Constants.USER_FIELD_EMAIL + ", " +
			Constants.USER_FIELD_FIRST_NAME + ", " +
			Constants.USER_FIELD_LAST_NAME + ", " +
			Constants.USER_FIELD_ENABLED + 
			" from " + this.userSchema + " where " + Constants.USER_FIELD_USER_ID + " = ?";
	}
	
	public String getSQLInsertNewUser() {
		return "insert into " + this.userSchema +
			"(" + Constants.USER_FIELD_USER_NAME + ", " + 
			Constants.USER_FIELD_PASSWORD + ", " + 
			Constants.USER_FIELD_ACTIVATION_ID + ", " +
			Constants.USER_FIELD_FIRST_NAME + ", " + 
			Constants.USER_FIELD_LAST_NAME + ", " + 
			Constants.USER_FIELD_EMAIL + ", " +
			Constants.USER_FIELD_ENABLED + ", " +
			Constants.USER_FIELD_NON_EXPIRED + ", " +
			Constants.USER_FIELD_NON_LOCKED + ", " +
			Constants.USER_FIELD_NON_CRED_EXPIRED + ") values " +
			"(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	}
	
	public String getSQLInsertUserAuthority() {
		return "insert into " + this.userRolesSchema +
			"(" + Constants.USER_FIELD_USER_ID + ", " + 
			Constants.USER_ROLE_FIELD_AUTH + ") values" +
			"(?, ?)";
	}
	
	public String getSQLSelectUserId() {
		return "select " + Constants.USER_FIELD_USER_ID +
			" from " + this.userSchema + " where " + 
			Constants.USER_FIELD_USER_NAME + " = ?";
	}
}
