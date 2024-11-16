package net.engdigest.journal.controller;

import net.engdigest.journal.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.script.ScriptTemplateConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class JournalEntryController {
//    private Map<Long, JournalEntry> journalEntries = new HashMap<>();
//
//    @GetMapping
//    public List<JournalEntry> getAll(){
//        return new ArrayList<>(journalEntries.values());
//    }
//    @PostMapping
//    public boolean CreatEntry(@RequestBody JournalEntry entry) {
//        journalEntries.put(entry.getId(), entry);
//        return true;
//    }
//
//    @GetMapping("/id/{myId}")
//    public JournalEntry getJournalEntryById(@PathVariable long myId){
//        return journalEntries.get(myId);
//    }
//    @DeleteMapping("/id/{myId}")
//    public JournalEntry deleteJournalId(@PathVariable long myId){
//        return journalEntries.remove(myId);
//    }
//    @PutMapping("/id/{myId}")
//    public JournalEntry updateJournalEntry(@PathVariable String myId, @RequestBody JournalEntry entry){
////        return journalEntries.put(entry.getId(), entry);
//        return null;
//    }
}
