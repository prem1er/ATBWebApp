package com.app.spring.service.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.app.spring.dbmap.AuthenticateUserRowMapper;
import com.app.spring.service.model.AuthenticatedUser;

public class UserAuthenticationDAOImpl implements UserDetailsService {

   private JdbcTemplate jdbcTemplate;
   
   private String sqlSelectUser;
   private String sqlSelectUserRoles;

   private Logger log; 
   
   public UserAuthenticationDAOImpl() {
	   this.log = Logger.getLogger(this.getClass());
   }
   
   public void setUserSQLSelect(String pDBSchema) {
	   this.sqlSelectUser = "select * from " + pDBSchema + " where USER_NAME = ?";
   }
   
   public void setUserRolesSQLSelect(String pDBSchema) {
	   this.sqlSelectUserRoles = "select AUTHORITY from " + pDBSchema + " where USER_ID = ?";
   }
   
   /**
    * @param pDataSource
    */
   public void setDataSource(DataSource pDataSource) {
      this.jdbcTemplate = new JdbcTemplate(pDataSource);
   }
   
   /* (non-Javadoc)
    * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
    */
   @Override
   public UserDetails loadUserByUsername(String pUserName) throws UsernameNotFoundException, DataAccessException {
	  getLog().info("Querying for User; [User Name: " + pUserName + "]");
	  
	  AuthenticatedUser user = null;
	  AuthenticateUserRowMapper authRowMapper = new AuthenticateUserRowMapper();
      user = this.jdbcTemplate.queryForObject(this.sqlSelectUser, authRowMapper, pUserName); 
      user.setAuthorities(this.getAuthorities(user.getUserId()));
      
      getLog().info("End query for User ..");
      return user;
   }
   
   /**
    * @param pUserId
    * @return List<SimpleGrantedAuthority>
    */
   private List<SimpleGrantedAuthority> getAuthorities(String pUserId) {
	  getLog().info("Querying for Authorities; [User ID: " + pUserId + "]");
      
	  String role = this.jdbcTemplate.queryForObject(this.sqlSelectUserRoles, String.class, pUserId);
      List<SimpleGrantedAuthority> authList = new ArrayList<SimpleGrantedAuthority>();
      authList.add(new SimpleGrantedAuthority(role));
      
      getLog().info("End query for Authorities ..");
      return authList;
   }

   public Logger getLog() {
	   return log;
   }   
}
