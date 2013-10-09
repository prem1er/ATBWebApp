package com.app.spring.dbmap;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.app.spring.service.Constants;
import com.app.spring.service.model.AuthenticatedUser;

public class AuthenticateUserRowMapper implements RowMapper<AuthenticatedUser> {
    
	@Override
    public AuthenticatedUser mapRow(ResultSet rs, int row) throws SQLException {
       return new AuthenticatedUser(rs.getString(Constants.USER_FIELD_USER_NAME), rs.getString(Constants.USER_FIELD_PASSWORD), 
    		   rs.getString(Constants.USER_FIELD_USER_ID), rs.getBoolean(Constants.USER_FIELD_ENABLED), 
    		   rs.getBoolean(Constants.USER_FIELD_NON_EXPIRED), rs.getBoolean(Constants.USER_FIELD_NON_LOCKED), 
    		   rs.getBoolean(Constants.USER_FIELD_NON_CRED_EXPIRED));
    }
}
