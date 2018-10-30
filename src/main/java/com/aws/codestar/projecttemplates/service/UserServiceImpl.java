package com.aws.codestar.projecttemplates.service;

import java.util.Arrays;
import java.util.HashSet;

import com.aws.codestar.projecttemplates.model.User;
import com.aws.codestar.projecttemplates.model.Role;
import com.aws.codestar.projecttemplates.repository.UserRepository;
import com.aws.codestar.projecttemplates.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
	public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
        
        FacebookConnectionFactory ffactory = new FacebookConnectionFactory(env.getProperty("facebook.app.id"), env.getProperty("facebook.app.secret"));
        ffactory.setScope(env.getProperty("facebook.scope"));
        cfConfig.addConnectionFactory(ffactory);
	}
	
    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setFirstname(user.getFirstname());
        user.setLastname(user.getLastname());
        user.setActive(1);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
}