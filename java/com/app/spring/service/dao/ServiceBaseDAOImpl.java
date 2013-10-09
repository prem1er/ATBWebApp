package com.app.spring.service.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class ServiceBaseDAOImpl implements ServiceBaseDAO {

	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource pDataSource) {
		this.jdbcTemplate = new JdbcTemplate(pDataSource);
	}
	
	protected JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}
	
/*	@Override
	public String getEncryptedPassword(String pInputPassword) {
		return SecurityMaster.encrypt(Constants.PASS_SEED, pInputPassword);
	}

	@Override
	public String getUserID(String pUserName) {
		return SecurityMaster.encrypt(Constants.USER_ID_SEED, pUserName);
	}*/
   
}
