package net.engdigest.journal.controller;

import net.engdigest.journal.entity.User;
import net.engdigest.journal.services.JournalEntryService;
import net.engdigest.journal.services.UserService;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll(){
         return userService.getAll();
    }

    @PostMapping
    public void CreatUser(@RequestBody User user) {
            userService.saveEntry(user);
    }


//    @GetMapping("/id/{myId}")
//    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){
//        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
//        if(journalEntry.isPresent()) {
//        	return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//
//    @DeleteMapping("/id/{myId}")
//    public ResponseEntity<?> deleteJournalId(@PathVariable ObjectId myId){
//        journalEntryService.deleteById(myId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }


    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){
       User userInDb = userService.findByUserName(userName);
       if(userInDb != null) {
    	   userInDb.setUserName(user.getUserName());
    	   userInDb.setPassword(user.getPassword());
    	   userService.saveEntry(userInDb);
       }
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}