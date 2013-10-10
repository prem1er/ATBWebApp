package com.app.spring.service.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.app.spring.service.model.ResponseBase;
import com.app.spring.service.model.ResponseUserData;

@Path("/userservice")
public interface UserService {

     @POST
     @Path("/createUser")
     @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
     @Produces(MediaType.APPLICATION_JSON)
     public ResponseBase createUser(@FormParam("userName") String pUserName, @FormParam("password") String pPassword, 
    		 @FormParam("email") String pEmail, @FormParam("firstName") String pFirstName, @FormParam("lastName") String pLastName);
     
     @POST
     @Path("/getUserInfo")
     @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
     @Produces(MediaType.APPLICATION_JSON)
     //@PreAuthorize("hasRole('ROLE_USER')")
     public ResponseUserData getUserInfo(@FormParam("userId") String pUserId);
     
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
}
