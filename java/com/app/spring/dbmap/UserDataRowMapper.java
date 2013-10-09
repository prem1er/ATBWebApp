package com.app.spring.dbmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.spring.service.model.UserData;

public class UserDataRowMapper implements RowMapper<UserData> {

    @Override
    public UserData mapRow(ResultSet rs, int row) throws SQLException {
       return new UserData(rs.getString("USER_NAME"), rs.getString("EMAIL"), 
             rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"));
    }
}
