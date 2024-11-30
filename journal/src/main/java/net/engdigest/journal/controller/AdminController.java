package net.engdigest.journal.controller;

import net.engdigest.journal.entity.JournalEntry;
import net.engdigest.journal.entity.User;
import net.engdigest.journal.repository.UserRepository;
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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> all = userService.getAll();
        if(all!= null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/get-user/{myId}")
    public ResponseEntity<?> getUserById(@PathVariable ObjectId myId) {
        Optional<User> user = userService.findById(myId);
        if(user.isPresent()){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-new-admin")
    public void signup(@RequestBody User user) {
        userService.saveNewAdmin(user);
    }

    @DeleteMapping("/{deleteUser}")
    public ResponseEntity<?> deleteOtherUser(@PathVariable String deleteUser) {
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        if(user.getRoles().contains("ADMIN")){
            userRepository.deleteByUserName(deleteUser);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
