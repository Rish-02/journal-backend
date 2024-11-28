package net.engdigest.journal.services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.engdigest.journal.entity.JournalEntry;
import net.engdigest.journal.entity.User;
import net.engdigest.journal.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {
    @Autowired

    private UserRepository userRepository;

    public void saveEntry(User user) {
//        	journalEntry.setDate(LocalDateTime.now());
        	userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
    	userRepository.deleteById(id);
    }
    public User findByUserName(String userName) {
    	return userRepository.findByUserName(userName);    
    }
}
//controller call service == best practice
//        service call repo