package net.engdigest.journal.repository;

import net.engdigest.journal.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
	User findByUserName(String userName);
	User deleteByUserName(String userName);
}
