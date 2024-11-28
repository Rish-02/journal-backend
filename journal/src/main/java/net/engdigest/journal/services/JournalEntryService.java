package net.engdigest.journal.services;

import net.engdigest.journal.entity.JournalEntry;
import net.engdigest.journal.entity.User;
import net.engdigest.journal.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService {
   
	@Autowired
    private JournalEntryRepository journalEntryRepository;
	
	@Autowired
	private UserService userService;

    @Transactional
	public void saveEntry(JournalEntry journalEntry, String userName) {
        	try {
        		User user = userService.findByUserName(userName);  //user nikala
            	journalEntry.setDate(LocalDateTime.now());
            	JournalEntry saved = journalEntryRepository.save(journalEntry); //journal entry save
            	user.getJournal_Entry().add(saved); //users find hua tha uski joiurnal entry m add rkadi h entry
//            	user.setUserName(null);
            	userService.saveEntry(user); //user saved with new journal entry
        	}catch(Exception e) {
        		System.out.print(e);
        		throw new RuntimeException("An error occured",e);
        	}
    }
    public void saveEntry(JournalEntry journalEntry) {
    	journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName){
    	User user = userService.findByUserName(userName);
    	user.getJournal_Entry().removeIf(x->x.getId().equals(id));
    	userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }
}
//controller call service == best practice
//        service call repo