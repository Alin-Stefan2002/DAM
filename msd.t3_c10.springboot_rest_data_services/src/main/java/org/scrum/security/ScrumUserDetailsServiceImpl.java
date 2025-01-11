package org.scrum.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ScrumUserDetailsServiceImpl implements UserDetailsService {
	private static Logger logger = Logger.getLogger(ScrumUserDetailsServiceImpl.class.getName());
	
    @Autowired
    private UserRepository userRepository;
     
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
    	
    	logger.info("loadUserByUsername: finding " + username);
    	
        User user = userRepository.findUserByUsername(username);
         
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        
        logger.info("loadUserByUsername: found " + user);
        
        return new ScrumUserDetails(user);
    }
 
}