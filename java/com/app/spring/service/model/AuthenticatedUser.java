package com.app.spring.service.model;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticatedUser implements UserDetails {

   private static final long serialVersionUID = 3433669434877715193L;
   
   private String username;
   private String userId;
   private String password;
   private String firstName;
   private String lastName;
   private String email;
   
   private boolean isAccountNonExpired;
   private boolean isAccountNonLocked;
   private boolean isCredentialsNonExpired;
   private boolean isEnabled;
   
   private List<SimpleGrantedAuthority> authList;
   
   public AuthenticatedUser(String pUsername, String pPassword, String pUserId, 
		   boolean pIsEnabled, boolean pIsAccountNonExpired, 
		   boolean pIsAccountNonLocked, boolean pIsCredentialsNonExpired) {
	   this.username = pUsername;
	   this.password = pPassword;
	   this.userId = pUserId;
	   this.isAccountNonExpired = pIsAccountNonExpired;
	   this.isAccountNonLocked = pIsAccountNonLocked;
	   this.isEnabled = pIsEnabled;
	   this.isCredentialsNonExpired = pIsCredentialsNonExpired;
   }
   
   public String getUserId() {
      return userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   @Override
   public List<SimpleGrantedAuthority> getAuthorities() {
      return this.authList;
   }
   
   public void setAuthorities(List<SimpleGrantedAuthority> authList) {
	   this.authList = authList;
   }

@Override
   public String getPassword() {
      return password;
   }

   @Override
   public String getUsername() {
      return username;
   }

   @Override
   public boolean isAccountNonExpired() {
      return isAccountNonExpired;
   }

   @Override
   public boolean isAccountNonLocked() {
        return isAccountNonLocked;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return isCredentialsNonExpired;
   }

   @Override
   public boolean isEnabled() {
      return isEnabled;
   }
}
