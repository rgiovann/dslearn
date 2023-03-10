package com.devsuperior.dslearnbds.services;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslearnbds.entities.User;
import com.devsuperior.dslearnbds.repositories.UserRepository;
import com.devsuperior.dslearnbds.services.exceptions.ForbiddenException;
import com.devsuperior.dslearnbds.services.exceptions.UnauthorizedException;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;

	@Transactional( readOnly = true)
	public User authenticated() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByEmail(username);
		Optional<User> opt = Optional.ofNullable(user);
		return opt.orElseThrow(() -> new UnauthorizedException("Invalid user: " + username ));
	}
	
	public void validateSelfOrAdmin(Long userId) {
		User  user = authenticated();
		if( !user.getId().equals(userId) && !user.hasRole("ROLE_ADMIN")   ) {
			throw new ForbiddenException("Access denied.");
		}
	}

}
