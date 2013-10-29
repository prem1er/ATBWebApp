package com.app.spring.util;

import org.springframework.security.core.context.SecurityContextHolder;

import com.app.spring.service.model.AuthenticatedUser;

public class AuthenticatedUserUtil {

	public static AuthenticatedUser getAuthenticatedUser() {
		return (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
