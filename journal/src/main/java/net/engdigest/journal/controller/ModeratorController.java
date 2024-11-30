package net.engdigest.journal.controller;

import net.engdigest.journal.entity.JournalEntry;
import net.engdigest.journal.entity.User;
import net.engdigest.journal.repository.UserRepository;
import net.engdigest.journal.services.JournalEntryService;
import net.engdigest.journal.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mod")
public class ModeratorController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JournalEntryService journalEntryService;

    @PostMapping("/disable-user/{userName}")
    public ResponseEntity<?> disableUser(@PathVariable String userName) {
        User user = userService.findByUserName(userName);
        if(user != null){
            user.setEnabled(false);
            userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/enable-user/{userName}")
    public ResponseEntity<?> enableUser(@PathVariable String userName) {
        User user = userService.findByUserName(userName);
        if(user != null){
            user.setEnabled(true);
            userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/journal/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId) {
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        boolean removed = journalEntryService.deleteById(myId, username);
        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
