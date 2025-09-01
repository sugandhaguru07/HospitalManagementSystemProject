package com.hospital.management.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hospital.management.entityClasses.Bill;

public interface BillRepository extends MongoRepository<Bill,String>{

}
