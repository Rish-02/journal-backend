package net.engdigest.journal.controller;

import net.engdigest.journal.entity.User;
import net.engdigest.journal.repository.UserRepository;
import net.engdigest.journal.services.JournalEntryService;
import net.engdigest.journal.services.UserService;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
    	
    	org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);
	    userInDb.setUserName(user.getUserName());
	    userInDb.setPassword(user.getPassword());
	    userService.saveNewEntry(userInDb);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @DeleteMapping
    public ResponseEntity<?> deleteAuthenticatedUser() {
    	org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String userName = authentication.getName();
    	userRepository.deleteByUserName(userName);

    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}