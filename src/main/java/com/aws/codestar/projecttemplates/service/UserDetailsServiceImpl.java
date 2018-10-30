package com.aws.codestar.projecttemplates.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.aws.codestar.projecttemplates.model.Role;
import com.aws.codestar.projecttemplates.model.User;
import com.aws.codestar.projecttemplates.repository.UserRepository;

public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {
	@Autowired
    private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 User user = userRepository.findByUsername(username);
		 Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		 
	     for (Role role : user.getRoles()){
	         grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
	     }

	     UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
	     
	     return userDetails;
	}
	
}
