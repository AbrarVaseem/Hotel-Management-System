package com.hms.usersystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hms.usersystem.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        return new User("foo", "foo",
//                new ArrayList<>());
//    }
	
	
	@Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.hms.usersystem.models.User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return new CustomUserDetails(user);
        
    }
	
//	@Override 
//	   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		com.hms.usersystem.models.User us = userRepository.findByUsername(username);
//	      if (username.equals(username)) { 
//	         return (UserDetails) new com.hms.usersystem.models.User(us.getId(), us.getUsername(), us.getPassword(), us.getRole()); 
//	      } else { 
//	         throw new UsernameNotFoundException("User not found with username: " + username); 
//	      } 
//	   }
	
//	 @Override
//	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//	        com.hms.usersystem.models.User user = userRepository.findByUsername(username);
//	        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
//	    }
	

	
	
}
