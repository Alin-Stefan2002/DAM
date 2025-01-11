package org.scrum.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;

public class ScrumUserDetails implements UserDetails {
	private static Logger logger = Logger.getLogger(ScrumUserDetails.class.getName());
	
    private User user;
     
    public ScrumUserDetails(User user) {
        this.user = user;
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	logger.info("User role for GrantedAuthority: " + user.getRole());
        SimpleGrantedAuthority authority =
        		//new SimpleGrantedAuthority("ROLE_" + user.getRole()); // for requestMatchers::hasRole
        		new SimpleGrantedAuthority(user.getRole());  // for requestMatchers::hasAuthority
        return Arrays.asList(authority);
    }
 
    @Override
    public String getPassword() {
        return user.getPassword();
    }
 
    @Override
    public String getUsername() {
        return user.getUsername();
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
 
}