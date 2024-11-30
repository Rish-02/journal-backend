package net.engdigest.journal.controller;

import lombok.extern.slf4j.Slf4j;
import net.engdigest.journal.entity.JournalEntry;
import net.engdigest.journal.repository.UserRepository;
import net.engdigest.journal.services.CustomUserDetailsServiceImpl;
import net.engdigest.journal.services.JournalEntryService;
import net.engdigest.journal.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import net.engdigest.journal.entity.User;
import net.engdigest.journal.services.UserService;
import java.util.List;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
	@Autowired
    private UserService userService;
    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "OK";
    }

    @GetMapping("/journal")
    public List<JournalEntry> openJournal() {
        return journalEntryService.getOpenJournalEntries().stream().toList();
    }
    
    @PostMapping("/signup")
    public void signup(@RequestBody User user) {
            userService.saveNewEntry(user);
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody User user) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
            if(!userDetails.isEnabled()){
                return new ResponseEntity<>("User is inactive, please contact a mod", HttpStatus.BAD_REQUEST);
            }
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e){
            log.error("Exception occurred while createAuthenticationToken ", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/csrf-token")  
    public String getCsrfToken(HttpServletRequest request){ // CsrfToken provide by spring security.
        // How generate token
        System.out.println((CsrfToken)request.getAttribute("_csrf"));
        return "OK";
    }
}
