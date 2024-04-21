package org.solo.repository;

import org.solo.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Users extends MongoRepository<User,String> {

    boolean existsByUsername(String username);

    User findByUsername(String username);

}
