package com.hospital.management.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hospital.management.entityClasses.UserAccount;

public interface UserAccountRepository extends MongoRepository<UserAccount,String>{
   Optional<UserAccount> findByUserName(String userName);
   boolean existsByUserName(String userName);
}
