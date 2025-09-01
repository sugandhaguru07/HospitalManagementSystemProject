package com.hospital.management.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hospital.management.entityClasses.Patient;

public interface PatientRepository extends MongoRepository<Patient,String>{

}
