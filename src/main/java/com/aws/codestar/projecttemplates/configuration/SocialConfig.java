package com.aws.codestar.projecttemplates.configuration;

import com.aws.codestar.projecttemplates.repository.UserRepository; 

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;

@Configuration
@EnableSocial
@PropertySource("classpath:social-cfg.properties")
public class SocialConfig implements SocialConfigurer {
	
	
	@Autowired
    private DataSource dataSource;
	
	@Autowired
    private UserRepository userRepository;
	
	
	@Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
        
        FacebookConnectionFactory ffactory = new FacebookConnectionFactory(env.getProperty("facebook.app.id"), env.getProperty("facebook.app.secret"));
        ffactory.setScope(env.getProperty("facebook.scope"));
        cfConfig.addConnectionFactory(ffactory);
        
        GoogleConnectionFactory gfactory = new GoogleConnectionFactory(env.getProperty("google.client.id"), env.getProperty("google.client.secret"));
        gfactory.setScope(env.getProperty("google.scope"));
        cfConfig.addConnectionFactory(gfactory);
 
	}

	@Override
	public UserIdSource getUserIdSource() {
		return new AuthenticationNameUserIdSource();
	}
	
	@Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository usersConnectionRepository = new JdbcUsersConnectionRepository(dataSource,connectionFactoryLocator,Encryptors.noOpText());
  
        usersConnectionRepository.setConnectionSignUp(null);
        
        return usersConnectionRepository;
    }
	
    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator,  ConnectionRepository connectionRepository) {
        return new ConnectController(connectionFactoryLocator, connectionRepository);
    }

}