package com.hospital.management.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hospital.management.entityClasses.Doctor;

public interface DoctorRepository extends MongoRepository<Doctor,String>{

}
