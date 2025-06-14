package com.mehedy.journal_app.repository;

import com.mehedy.journal_app.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {

    public User findUserByUsername(String username);
    public void deleteByUsername(String username);
}
