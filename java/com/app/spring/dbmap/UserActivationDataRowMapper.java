package com.app.spring.dbmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.spring.service.Constants;
import com.app.spring.service.model.UserData;

public class UserActivationDataRowMapper implements RowMapper<UserData> {

    @Override
    public UserData mapRow(ResultSet rs, int row) throws SQLException {
       return new UserData(rs.getString(Constants.USER_FIELD_ACTIVATION_ID), rs.getInt(Constants.USER_FIELD_USER_ID),
    		   rs.getBoolean(Constants.USER_FIELD_ENABLED));
    }
}
