package com.teamx.grocery.repository;

import com.teamx.grocery.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {


    @Query("{ 'username' : ?0}")
    Optional<User> checkIfEmailExists(String username);
    @Query("{ 'username' : ?0,'password': ?1 }")
    Optional<User> findUserWithHash(String username, String password);



}
