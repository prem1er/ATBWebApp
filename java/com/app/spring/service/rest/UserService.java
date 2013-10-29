package com.app.spring.service.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.security.access.prepost.PreAuthorize;

import com.app.spring.service.model.ResponseBase;
import com.app.spring.service.model.ResponseUserData;

@Path("/userservice")
public interface UserService {

     @POST
     @Path("/createUser")
     @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
     @Produces(MediaType.APPLICATION_JSON)
     public ResponseBase createUser(@FormParam("userName") String pUserName, 
    		 @FormParam("password") String pPassword, 
    		 @FormParam("email") String pEmail, 
    		 @FormParam("firstName") String pFirstName, 
    		 @FormParam("lastName") String pLastName);
     
     @POST
     @Path("/getUserInfo")
     @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
     @Produces(MediaType.APPLICATION_JSON)
     @PreAuthorize("hasRole('ROLE_USER')")
     public ResponseUserData getUserInfo();
     
     @POST
     @Path("/validateUserName")
     @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
     @Produces(MediaType.APPLICATION_JSON)
     public ResponseBase validateUserName(@FormParam("userName") String pUserName);
     
     @POST
     @Path("validateEmail")
     @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
     @Produces(MediaType.APPLICATION_JSON)
     public ResponseBase validateEmail(@FormParam("email") String pEmail);
     
     @POST
     @Path("/activateUser")
     @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
     @Produces(MediaType.APPLICATION_JSON)
     public ResponseBase activateUser(@FormParam("userId") String pUserId, 
    		 @FormParam("activationId") String pActivationId);
}
