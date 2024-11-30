package net.engdigest.journal.repository;

import lombok.NonNull;
import net.engdigest.journal.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {

    List<JournalEntry> findByVisibility(@NonNull String visibility);
}
