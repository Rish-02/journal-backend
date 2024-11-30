package net.engdigest.journal.services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.engdigest.journal.entity.JournalEntry;
import net.engdigest.journal.entity.User;
import net.engdigest.journal.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    private static final PasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();

    @Transactional
    public void saveNewEntry(User user) {
//        	journalEntry.setDate(LocalDateTime.now());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("USER"));
    	userRepository.save(user);
    }

    public void saveNewAdmin(User user) {
//        	journalEntry.setDate(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("MOD", "ADMIN"));
        userRepository.save(user);
    }
    
    public void saveUser(User user) {
    	userRepository.save(user);
	}

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteAuthenticatedUser(ObjectId id){
    	userRepository.deleteById(id);
    }
    public User findByUserName(String userName) {
    	return userRepository.findByUserName(userName);    
    }
}
