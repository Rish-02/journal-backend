package net.engdigest.journal.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NonNull;

@Document(collection = "users")
@Data
public class User {
	@Id
	private ObjectId id;
	
	@Indexed(unique=true)
	@NonNull
	private String userName;
	@NonNull
	private String password;
	
	private List<String> roles;
	@NonNull
	private boolean enabled;
	@DBRef
	private List<JournalEntry> journal_Entry= new ArrayList<>();
}